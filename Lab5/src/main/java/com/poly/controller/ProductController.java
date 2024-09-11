package com.poly.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.poly.dao.ProductDAO;
import com.poly.entity.Product;
import com.poly.entity.*;

@Controller
public class ProductController {
	@Autowired
	ProductDAO dao;
	
	@GetMapping("/product/sort")
	public String sort(Model m, @RequestParam("field")Optional<String> field) {
		Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
		m.addAttribute("field", field.orElse("price").toUpperCase());
		List<Product> list = dao.findAll(sort);
		m.addAttribute("list", list);
		return "sort";
	}
	
	@GetMapping("/product/sort?field=name")
	public String sortName(Model m, @RequestParam("field") String field) {
		Sort sort = Sort.by(Direction.DESC, field);
		m.addAttribute("field", field.toUpperCase());
		List<Product> list = dao.findAll(sort);
		m.addAttribute("list", list);
		return "sort";
	}
	
	@GetMapping("/product/sort?field=id")
	public String sortId(Model m, @RequestParam("field") String field) {
		Sort sort = Sort.by(Direction.DESC, field);
		m.addAttribute("field", field.toUpperCase());
		List<Product> list = dao.findAll(sort);
		m.addAttribute("list", list);
		return "sort";
	}
	
	@GetMapping("/product/sort?field=createDate")
	public String sortDate(Model m, @RequestParam("field") String field) {
		Sort sort = Sort.by(Direction.DESC, field);
		m.addAttribute("field", field.toUpperCase());
		List<Product> list = dao.findAll(sort);
		m.addAttribute("list", list);
		return "sort";
	}
}
	