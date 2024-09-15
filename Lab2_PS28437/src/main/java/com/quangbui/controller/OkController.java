package com.quangbui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OkController {
	@RequestMapping("/ok")
	public String ok() {
		return "ok";
	}

	@RequestMapping("/ok1")
	public String m1() {
		return "ok";
	}

	@RequestMapping("/ok2")
	public String m2() {
		return "ok";
	}

	@RequestMapping("/ok3")
	public String m3() {
		return "ok";
	}
}