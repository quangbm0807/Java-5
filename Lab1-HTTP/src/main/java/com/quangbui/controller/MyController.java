// File: ProvinceController.java
package com.quangbui.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quangbui.model.Province;
import com.quangbui.service.ProvinceService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller

public class MyController {

	@Autowired
	private ProvinceService provinceService;
	@Autowired
	ServletContext application;
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@GetMapping("/")
	public String index(Model model) {
		List<Province> provinces = provinceService.getAllProvinces();
		model.addAttribute("provinces", provinces);
		return "index";
	}

	@PostMapping("/register")
	public String handleRegistration(@RequestParam("lastName") String lastName,
									@RequestParam("firstName") String firstName, 
									@RequestParam("province") String province,
									@RequestParam("district") String district, 
									@RequestParam("ward") String ward,
									@RequestParam("file") MultipartFile file, 
									Model model) throws IOException {
		model.addAttribute("lastName", lastName);
		model.addAttribute("firstName", firstName);
		model.addAttribute("province", province);
		model.addAttribute("district", district);
		model.addAttribute("ward", ward);
		if (!file.isEmpty()) {
			String uploadDir = "src/main/resources/static/uploads/";
			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(uploadDir + fileName);
			Files.createDirectories(filePath.getParent());
			Files.write(filePath, file.getBytes());

			model.addAttribute("fileName", fileName);
			model.addAttribute("imagePath", "/uploads/" + fileName);
		}
		return "hello";
	}

}