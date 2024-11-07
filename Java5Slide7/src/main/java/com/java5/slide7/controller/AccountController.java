package com.java5.slide7.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java5.slide7.dao.AccountDAO;
import com.java5.slide7.entity.Account;
import com.java5.slide7.helper.AccountExcelExporter;
import com.java5.slide7.service.SessionService;

import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;


@Controller
public class AccountController {
	Logger logger = LogManager.getLogger(AccountController.class);
	@Autowired
	AccountDAO dao;
	@Autowired
	SessionService session;
	//Export the list of account to excel
	@GetMapping("/user/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Account> listUsers = dao.findAll();
        System.out.println(listUsers);
         
        AccountExcelExporter excelExporter = new AccountExcelExporter(listUsers);
         
        excelExporter.export(response);    
    }  
 
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
	@GetMapping("/account/logout")
	public String accountLogout() {
		Account accountLogin = (Account)session.get("user");
		if(accountLogin != null)
			session.set("user", null);
		return "/account/login";
	}
	
	@PostMapping("/account/login")
	public String login(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password) {
		logger.info("User Login: " + username);
		try {
			Account user = dao.findByUsername(username);
			if (user==null || !user.getPassword().equals(password)) {
				logger.warn("Login Fail: " + username);
				model.addAttribute("message", "Invalid username or password!");
			} else {
				session.set("user", user);
				String uri = (String) session.get("security-uri");
				logger.info("Login success: " + username);
				if (uri != null) {
					return "redirect:" + uri;
					//return "admin/themxoasua"; // sua
				} else {					
					model.addAttribute("message", "Login succeed");
					return "admin/themxoasua";
				}
				
			}
		} catch (Exception e) {
			logger.error("Error Login: " + username);
			model.addAttribute("message", "Invalid username");
		}
		return "account/login";
	}
}