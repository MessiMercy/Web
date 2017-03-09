package com.web.controller;

import com.web.model.News;
import com.web.service.NewsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/2/9.
 */
@Controller
@RequestMapping(value = {"/news", "/wenshu"})
public class SearchController {

    @Resource
    private NewsService service;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "news";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(@RequestParam("keyWords") String keyWords, @RequestParam("size") String size, @RequestParam("from") String from,
                         @RequestParam("time") String time, HttpServletRequest requst, Model model, HttpSession session) {
        String url = requst.getRequestURI();
        System.out.println("detecting connect from " + url);
        System.out.println(time);
        int intSize = 10;
        int intFrom = 0;
        if (StringUtils.isNoneEmpty(size, from)) {
            try {
                intSize = Integer.parseInt(size);
                intFrom = Integer.parseInt(from);
            } catch (Exception ignore) {
            }
        }
        List<News> newses;
        if (url.contains("wenshu")) {
            newses = service.generateNews("wenshu", keyWords, intSize, intFrom, time);
        } else {
            newses = service.generateNews("news", keyWords, intSize, intFrom, time);
        }
        model.addAttribute("newss", newses);
        return "content";
    }

    @RequestMapping(value = "crawl", method = RequestMethod.POST)
    public String crawl(@RequestParam("keyWords") String keyWords, HttpServletRequest request) {
        service.crawlNews(keyWords);
        return "news";
    }


}
