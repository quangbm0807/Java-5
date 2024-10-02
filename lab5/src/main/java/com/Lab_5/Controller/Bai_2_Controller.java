package com.Lab_5.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.Lab_5.DAO.CategoryDAO;
import com.Lab_5.Entity.Categories;

@Controller
public class Bai_2_Controller {
	@Autowired
	private CategoryDAO categoryDAO = null;

	@GetMapping("/")
	public String index(Model model) {
		Categories item = new Categories();
		model.addAttribute("item", item);
		List<Categories> items = categoryDAO.findAll();
		model.addAttribute("items", items);
		return "Bai_2";
	}

	@GetMapping("/edit/{Id}")
	public String edit(Model model, @PathVariable("Id") String id) {
		Categories item = categoryDAO.findById(id).get();
		model.addAttribute("item", item);
		List<Categories> items = categoryDAO.findAll();
		model.addAttribute("items", items);
		return "Bai_2";
	}

	@PostMapping(value = "/add")
	public String add(Categories item, Model model) {
		if (item.getId() != null && categoryDAO.existsById(item.getId())) {
			model.addAttribute("error", "ID đã được sử dụng hoặc không đúng định dạng!");
		} else {
			categoryDAO.save(item);
			model.addAttribute("message", "Thêm thành công!");
		}
		model.addAttribute("item", item);
		// Load lại danh sách các mục
		List<Categories> items = categoryDAO.findAll();
		model.addAttribute("items", items);
		return "Bai_2";
	}

	@PostMapping("/update")
	public String update(Categories item, Model model) {
		if (item.getId() == null || !categoryDAO.existsById(item.getId())) {
			model.addAttribute("error", "Không có ID này!");
		} else {
			categoryDAO.save(item);
			model.addAttribute("message", "Sửa thành công!");
		}
		model.addAttribute("item", item);
		List<Categories> items = categoryDAO.findAll();
		model.addAttribute("items", items);
		return "Bai_2";
	}

	@GetMapping("/delete/{Id}")
	public String create(@PathVariable("Id") String id) {
		categoryDAO.deleteById(id);
		return "redirect:/";
	}
}
