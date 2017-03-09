package com.web.service;

import com.downloader.Downloader;
import com.downloader.Request;
import com.downloader.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.parser.Json;
import com.processer.SinaNewsProcesser;
import com.web.model.News;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2017/2/24.
 */
@Service
public class NewsService {
    private Downloader downloader = new Downloader();
    private JedisPool pool = new JedisPool("127.0.0.1");

    @Async
    public void crawlNews(String keyWords) {
        SinaNewsProcesser processer = new SinaNewsProcesser(pool, "news");
        processer.searchButton(keyWords);
    }

    public List<News> generateNews(String collection, String keyWords, int size, int from, String searchTime) {
        List<News> news = new ArrayList<>();
        Request request;
        if (collection.contains("wenshu")) {
            request = new Request(String.format
                    ("http://localhost:9200/wenshu/_search?pretty&q=%s+date:%s&size=%d&from=%d",
                            keyWords, searchTime, size, from));
        } else {
            request = new Request(String.format("http://localhost:9200/test/news0," +
                            "news1,news2/_search?pretty&q=%s+time:%s&size=%d&from=%d",
                    keyWords, searchTime, size, from));
        }
        if (StringUtils.isEmpty(searchTime)) {
            String replaceUrl = request.getUrl().replaceAll("\\+(time|date):", "");
            request.setUrl(replaceUrl);
        }
//        request = new Request();
        request.setSleepTime(0);
        downloader.setDelayTime(0);
        Response process = downloader.process(request);
        String content = process.getContent();
        Json json = new Json(content);
        JsonArray asJsonArray = json.getEle("hits.hits").getAsJsonArray();
        for (JsonElement element : asJsonArray) {
            News news0 = new News();
            String newsContent = " ";
            String newsTitle = " ";
            String url = " ";
            String time = " ";
            String score = " ";
            JsonObject source;
            try {
                source = element.getAsJsonObject().get("_source").getAsJsonObject();
                newsTitle = source.get("title").getAsString();
                score = element.getAsJsonObject().get("_score").getAsString();
                if (collection.contains("wenshu")) {
                    newsContent = source.get("detail").getAsString();
                    time = source.get("date").getAsString();
                    url = "http://wenshu.court.gov.cn/content/content?DocID=" + source.get("DocId").getAsString();
                } else {
                    newsContent = source.get("content").getAsString();
                    url = source.get("url").getAsString();
                    time = source.get("time").getAsString();
                }
            } catch (Exception ignored) {
            }
            news0.setUrl(Optional.of(url).orElse("/"));
            news0.setTime(Optional.of(time).orElse("0"));
            news0.setScore(Optional.of(score).orElse("0"));
            news0.setTitle(Optional.of(newsTitle).orElse("empty"));
            news0.setContent(Optional.of(newsContent).orElse("empty"));
            news.add(news0);
        }
        System.out.println("总计找到新闻条数: " + news.size());
        return news;

    }
}
