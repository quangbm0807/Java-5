package com.java5.slide3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.java5.slide3.model.Staff3;

import jakarta.validation.Valid;

@Controller
public class Staff3Controller {
	@GetMapping("staff3/index")
	public String index(Model model) {
		Staff3 staff3 = new Staff3();
//		staff3.setId("taila01");
//		staff3.setEmail("taict@fpt.edu.vn");
//		staff3.setSalary(10000.0);
//		staff3.setFullName("Lê Anh Tài");
//		staff3.setGender(true);
//		staff3.setPosition("CEO");
		model.addAttribute("staff3", staff3);
		return "staff3Form";
	}
	@PostMapping("staff3/save")
	public String save(Model model,@Valid @ModelAttribute("staff3") Staff3 staff3, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("message", "Some fields are not valid. Please fix!");
		}
		else {
			model.addAttribute("message", "All fields are valid!");
			staff3.setFullName(staff3.getFullName().toUpperCase());
		}
		
		return "staff3Form";
	}

}
