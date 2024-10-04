package com.java5.slide6.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java5.slide6.dao.ProductDAO;
import com.java5.slide6.entity.Product;
import com.java5.slide6.entity.Report;
@Controller
public class ProductController {
	@Autowired
	ProductDAO dao;
	@RequestMapping("/product/search")
	public String search(Model model, 
			@RequestParam("min") Optional<Double> min,
			@RequestParam("max") Optional<Double> max) {

		List<Product> items = dao.findAll();
		if(min.isPresent() && max.isPresent())
			items = dao.findByPriceBetween(min.get(), max.get());
		model.addAttribute("items", items);
		return "product/search";
	}
	@ResponseBody
	@RequestMapping("/product/report")
	public List<Report> productReport(){
		return dao.getInventoryByCategory();
	}
	@ResponseBody
	@RequestMapping("/product/reportByPrice")
	public List<Product> getProductByPrice(@RequestParam("price") double price){
		return dao.getProductGreaterThanPrice(price);
	}

}
