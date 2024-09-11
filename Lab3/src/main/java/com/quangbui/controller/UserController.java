package com.quangbui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quangbui.model.User;
import com.quangbui.model.UserDAO;

@Controller

public class UserController {
	@RequestMapping("/")
	public String addOrEdit(ModelMap model) {
		User user = new User();
		model.addAttribute("USER", user);
		model.addAttribute("ACTION", "/saveOrUpdate");

		return "register-user";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute("USER") User user) {
		UserDAO dao = new UserDAO();
		dao.save(user);
		model.addAttribute("USER", user);
		model.addAttribute("ACTION", "/saveOrUpdate");

		return "register-user";
	}

	@GetMapping("/edit/{username}")
	public String edit(ModelMap model, @PathVariable(name = "username") String username) {
		UserDAO dao = new UserDAO();
		User user = dao.findByUsername(username);
		model.addAttribute("USER", user);
		model.addAttribute("ACTION", "/saveOrUpdate");

		return "register-user";
	}

	@RequestMapping("/list")
	public String list(ModelMap model) {
		UserDAO dao = new UserDAO();
		model.addAttribute("USERS", dao.getAll());
		return "view-user";
	}
	@GetMapping("/delete/{username}")
	public String delete(ModelMap model, @PathVariable(name = "username") String username) {
		UserDAO dao = new UserDAO();
		int i = dao.delete(username);
		model.addAttribute("USERS", dao.getAll());

		return "view-user";
	}
}
