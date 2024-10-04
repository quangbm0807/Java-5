package com.java5.slide6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java5.slide6.dao.AccountDAO;
import com.java5.slide6.entity.Account;
import com.java5.slide6.service.SessionService;



@Controller
public class AccountController {
	@Autowired
	AccountDAO dao;
	@Autowired
	SessionService session;

	@GetMapping("/account/login")
	public String login() {
		return "account/login";
	}
	@GetMapping("/admin/index")
	public String adminInex() {
		return "admin/index";
	}
	@GetMapping("/admin/loginIndex") // la vao trang them, xoa, sua
	public String loginIndex() {
		return "admin/loginIndex";
	}

	@GetMapping("/admin/themxoasua") // la vao trang them, xoa, sua
	public String loginIndex1() {
		return "admin/themxoasua";
	}
	
	@PostMapping("/account/login")
	public String login(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		try {
			Account user = dao.findByUsername(username);
			if (user==null || !user.getPassword().equals(password)) {
				model.addAttribute("message", "Invalid password");
			} else {
				session.set("user", user);
				String uri = (String) session.get("security-uri");
				if (uri != null) {
					return "redirect:" + uri;
					//return "admin/themxoasua"; // sua
				} else {					
					model.addAttribute("message", "Login succeed");
					return "admin/themxoasua";
				}
			}
		} catch (Exception e) {
			model.addAttribute("message", "Invalid username");
		}
		return "account/login";
	}
}