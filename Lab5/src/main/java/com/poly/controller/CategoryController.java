package com.poly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.dao.CategoryDAO;
import com.poly.entity.Category;

@Controller
@Transactional
public class CategoryController {
	@Autowired
	CategoryDAO repoCate;

	@GetMapping("/category/index")
	public String index(Model model) {
		Category entity = new Category();
		model.addAttribute("category", entity);
		List<Category> list = repoCate.findAll();
		model.addAttribute("list", list);
		return "index";
	}
	
	@GetMapping("/category/reset")
	public String reset() {
		return "redirect:/category/index";
	}


	@GetMapping("/category/edit/{id}")
	public String edit(Model model, @ModelAttribute("category") Category entity, @PathVariable("id") String id) {
		entity = repoCate.getOne(id);
		model.addAttribute("category", entity);
		List<Category> list = repoCate.findAll();
		model.addAttribute("list", list);
		return "index";
	}

	@PostMapping("/category/save")
	public String save(Model model, @ModelAttribute("category") Category entity) {
		repoCate.save(entity);
		return "redirect:/category/index";
	}

	@PostMapping("/category/update/{id}")
	public String update(Model model, @ModelAttribute("category") Category entity) {
		repoCate.save(entity);
		return "redirect:/category/index";
	}

	@GetMapping("/category/delete/{id}")
	public String delete(Model model, @PathVariable("id") String id) {
		repoCate.deleteById(id);
		return "redirect:/category/index";
	}
}
