package com.java5.slide3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.java5.slide3.model.Staff;

@Controller
public class StaffController {
	@GetMapping("staff/index")
	public String index(Model model) {
		Staff staff = new Staff();
		model.addAttribute("staff", staff);
		return "staffForm";
	}
	@PostMapping("staff/save")
	public String save(@ModelAttribute("staff") Staff staff) {
		staff.setFullName(staff.getFullName().toUpperCase());
		staff.setSalary(staff.getSalary()*1.5);
		return "staffForm";
	}
}
