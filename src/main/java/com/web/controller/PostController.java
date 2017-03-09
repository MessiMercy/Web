package com.web.controller;

import com.web.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.web.properties.PostProperty;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostProperty property;

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String createPage(@Valid Post post, BindingResult result) {
        if (result.hasErrors()) {
            return "create";
        }
        post = property.save(post);
        return "redirect:/posts/" + post.getId();
    }



    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreatePage(Model model, HttpSession session) {
        model.addAttribute("post", new Post());
        // session.setAttribute("isLogin", true);
        return "create";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String get(@PathVariable long id, Model model, HttpSession session) {
        if (!session.getAttributeNames().hasMoreElements()) {
            return "redirect:/login";
        }
        // Post post = Data.getId(id);
        model.addAttribute("content", "欢迎你，" + id);
        model.addAttribute("post", property.findOne(id));
        return "post";
    }

}
