package com.java5.slide7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	
	@RequestMapping("/home/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/home/about")
	public String about() {
		return "home/about";
	}
}
