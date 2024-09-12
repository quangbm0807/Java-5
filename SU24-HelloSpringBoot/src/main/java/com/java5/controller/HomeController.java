package com.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}
//	@RequestMapping("/")
//	public String homePage(Model model) {
//		model.addAttribute("message", "Welcome to the first Springboot Application!");
//		System.out.println("index");
//		return "index";
//	}
}