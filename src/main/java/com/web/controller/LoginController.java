package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    Optional<String> next;

    @RequestMapping(method = RequestMethod.GET)
    String login(HttpSession session, @RequestParam("next") Optional<String> next) {
        System.out.println(next.orElse("null"));
        // next = Optional.of(next.get());
        if (session.getAttribute("isLogin") != null) {
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    String verifyLogin(@RequestParam("userName") String userName, @RequestParam("pw") String pw,
                       @RequestParam("next") Optional<String> next, HttpSession session, Model model) {
        // Optional<String> next =
        // Optional.ofNullable(session.getAttribute("next").toString());
        // System.out.println(next.orElse("111"));
        if (userName.contains("liu") && pw.contains("liu")) {
            session.setAttribute("isLogin", true);
            System.out.println("redirect:".concat(next.orElse("/")) + "---------------");
            return "redirect:".concat(next.orElse("/"));
        }
        return "redirect:/";
    }
}
