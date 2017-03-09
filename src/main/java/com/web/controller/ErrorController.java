package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Controller
public class ErrorController {

	@RequestMapping("/404")
	public String error404() {
		return "error/404";
	}

}
