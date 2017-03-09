package com.web.controller;

import com.google.gson.Gson;
import com.web.model.IPModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2017/1/17.
 */
@Controller
@RequestMapping("/ip")
public class IpController {
    private JedisPool pool = new JedisPool("127.0.0.1", 6379);
    private Gson gson = new Gson();

    @RequestMapping(method = RequestMethod.GET, value = "")
    String getOneIp() {
        return "forward:/ip/1";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/{ip}")
    String getManyIp(@PathVariable int ip, HttpSession session, Model model) {
        try (Jedis jedis = pool.getResource()) {
            List<String> availableIp = jedis.srandmember("availableIp", ip);
            List<String> collect = availableIp.stream().map(p -> {
                IPModel model1 = gson.fromJson(p, IPModel.class);
                return model1.getHost() + ":" + model1.getPort();
            }).collect(Collectors.toList());
            model.addAttribute("ips", collect);
            StringBuilder builder = new StringBuilder();
            collect.forEach(p -> builder.append(p + "\n"));
            return builder.toString();
        }
    }


}
