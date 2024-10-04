package com.java5.slide6.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java5.slide6.dao.ClazzDAO;
import com.java5.slide6.dao.ClazzDAO;
import com.java5.slide6.entity.Category;
import com.java5.slide6.entity.Clazz;

@Controller
public class ClazzController {
	@Autowired
	ClazzDAO dao;

	@RequestMapping("/clazz/index")
	public String index(Model model) {
		Clazz item = new Clazz();
		model.addAttribute("item", item);
		List<Clazz> items = dao.findAll();
		model.addAttribute("items", items);
		return "clazz/index";
	}

	@RequestMapping("/clazz/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Clazz item = dao.findById(id).get();
		model.addAttribute("item", item);
		List<Clazz> items = dao.findAll();
		model.addAttribute("items", items);
		return "clazz/index";
	}

	@RequestMapping("/clazz/create")
	public String create(Clazz item) {
		dao.save(item);
		return "redirect:/clazz/index";
	}

	@RequestMapping("/clazz/update")
	public String update(Clazz item) {
		dao.save(item);
		return "redirect:/clazz/edit/" + item.getId();
	}

	@RequestMapping("/clazz/delete/{id}")
	public String create(@PathVariable("id") Long id) {
		dao.deleteById(id);
		return "redirect:/clazz/index";
	}
	
	@RequestMapping("/clazz/search")
	public String paginate(Model model, @RequestParam("minStudent") Optional<Integer> minStudent,
			@RequestParam("maxStudent") Optional<Integer> maxStudent) {
		List<Clazz> items; 
		if(minStudent.isPresent() && maxStudent.isPresent())
			items = dao.findByNumberOfStudentsBetween(minStudent.get(), maxStudent.get());
		else
			items = dao.findAll();
		model.addAttribute("items", items);
		return "/clazz/searchClazzByStudent";
	}
	
	@RequestMapping("/clazz/searchClazzByStudentCount")
	@ResponseBody
	public List<Clazz> findByNumberOfStudentsBetween(
	    @RequestParam("min") Integer min, 
	    @RequestParam("max") Integer max) {
	    return dao.findByNumberOfStudentsBetween(min, max);
	}
	
	@RequestMapping("/clazz/findBySemester")
	@ResponseBody
	public List<Clazz> findBySemester(
	    @RequestParam("semester") String semester) {
	    return dao.findBySemesterContaining(semester);
	}
	
	
	@RequestMapping("/clazz/findByNameContaining")
	@ResponseBody
	public List<Clazz> findByNameContaining(
	    @RequestParam("name") String name) {
	    return dao.findByNameContaining(name);
	}
	
	@RequestMapping("/clazz/findByNameAndSemester")
	@ResponseBody
	public List<Clazz> findByNameAndSemester(
	    @RequestParam("name") String name, @RequestParam("semester") String semester) {
	    return dao.findByNameAndSemesterContaining(name,semester);
	}
}
