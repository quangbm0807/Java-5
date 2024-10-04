package com.java5.slide6.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java5.slide6.dao.CategoryDAO;
import com.java5.slide6.entity.Category;
@Controller
public class Category1Controller {
	@Autowired
	CategoryDAO dao;
	
	
	@RequestMapping("/category1/sort")
	public String index(Model model, @RequestParam("field") Optional<String> field) {
		Sort sort = Sort.by(Direction.ASC, field.orElse("name"));
		List<Category> categories = dao.findAll(sort);
		model.addAttribute("items", categories);
		return "/category1/index";
	}
	
	@RequestMapping("/category/page")
	public String paginate(Model model, @RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 2);
		Page<Category> pages = dao.findAll(pageable);
		model.addAttribute("page", pages);
		return "/pagination/index";
	}
}
