package com.quangbui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {
	@GetMapping("/hello")
	public String showHello() {
		return "hello";
	}

	@GetMapping("/")
	public String showHi() {
		return "hello";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/loginSuccess")
	public String loginSuccess(HttpServletRequest req) {
		String userName = req.getParameter("username");
		String passsWord = req.getParameter("password");
		req.setAttribute("username", userName);
		req.setAttribute("password", passsWord);
		return "loginSuccess";
	}
}
