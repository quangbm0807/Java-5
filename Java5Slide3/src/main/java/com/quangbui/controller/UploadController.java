package com.quangbui.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Controller
public class UploadController {
	@Autowired
	ServletContext app;
	@GetMapping("upload/form")
	public String form() {
		return "upload/form";
	}
	@PostMapping("upload/save")
	public String send(@RequestParam("attach") MultipartFile attach, Model model) 
			throws IllegalStateException, IOException {
		if(!attach.isEmpty()) {
			String fileName = attach.getOriginalFilename();
			
			File file = new File(app.getRealPath("/docs/") + fileName);
			System.out.println(app.getRealPath("/docs/"));
			attach.transferTo(file);
		}else
			System.out.println("The file is empty!");
		model.addAttribute("message", "Upload attachment file successful!");
		return "upload/success";
	}
}
