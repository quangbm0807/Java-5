package com.poly.controller;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
@Autowired
HttpSession session;

@Autowired
HttpServletRequest rep;

@Autowired
HttpServletResponse resp;

@Autowired
AccountDAO dao;

@GetMapping("/login")
public String loginForm() {
	return "login";
}

@PostMapping("/login")
public String loginProccess(@RequestParam("uname") String uname,@RequestParam("passwd") String passd,Model m)throws IOException{
	Optional<Account> acc = dao.findById(uname);
	if(acc.isPresent() && acc.get().getPassword().equals(passd)) {
		String url = (String) rep.getSession().getAttribute("UrtSecure");
		if(url != null) {
			resp.sendRedirect(url);
		}else {
			resp.sendRedirect("https://google.com");
			
			}
		
		}
	m.addAttribute("message","Đăng nhập thất bại!");

	return "login";
}

	@ResponseBody
	@GetMapping("/info")
	public Account getLoginName() {
		return (Account) rep.getSession().getAttribute("taikhoan");
		
	}
	@ResponseBody
	@GetMapping("/info/{id}")
	public Account getInfoUser(@PathVariable("id") String id) {
		Optional<Account> oAcc = dao.findById(id);
		if(oAcc.isPresent())
			return oAcc.get();
		else
			return new Account();
	
}
}

