package com.quangbui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomepageController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/triangle")
	public String triangle(@RequestParam("canh1") double a, @RequestParam("canh2") double b,
			@RequestParam("canh3") double c, Model model) {
		String loaiTamGiac = "Không phải là tam giác";
		if (a + b > c && a + c > b && b + c > a) {
			if (a == b && b == c) {
				loaiTamGiac = "Tam giác đều";
			} else if (a == b || b == c || c == a) {
				loaiTamGiac = "Tam giác cân";
			} else if (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)
					|| Math.pow(a, 2) + Math.pow(c, 2) == Math.pow(b, 2)
					|| Math.pow(b, 2) + Math.pow(c, 2) == Math.pow(a, 2)) {
				loaiTamGiac = "Tam giác vuông";
			} else {
				loaiTamGiac = "Tam giác thường";
			}
		}
		model.addAttribute("result", loaiTamGiac);
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		model.addAttribute("c", c);
		return "index";
	}
}
