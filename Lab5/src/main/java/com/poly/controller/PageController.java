package com.poly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.poly.dao.ProductDAO;
import com.poly.entity.Product;


@Controller
public class PageController {
	@Autowired
	ProductDAO dao;
	
	@GetMapping("/product/page")
	public String paginate(Model model, @RequestParam("p") Optional<Integer> p){
		Pageable pageable;
		try {
			pageable = PageRequest.of(p.orElse(0), 5);
		} catch (Exception e) {
			pageable = PageRequest.of(0, 5);
		}
		Page<Product> page = dao.findAll(pageable);
		model.addAttribute("list", page);
		return "page";
	}
}
