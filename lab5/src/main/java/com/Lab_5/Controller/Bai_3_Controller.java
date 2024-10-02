package com.Lab_5.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Lab_5.DAO.ProductDAO;

@Controller
public class Bai_3_Controller {

	@Autowired
	private ProductDAO productDAO = null;

	@GetMapping("/bai3")
	private String Bai3(Model model) {
		model.addAttribute("items", productDAO.findAll());
		return "Bai_3";
	}

	@GetMapping("/sort")
	public String sort(Model model, @RequestParam("field") Optional<String> field) {
	    Sort sort = Sort.by(Direction.DESC, field.orElse("price"));
	    System.out.println(field);
	    model.addAttribute("field", field.orElse("price").substring(0, 1).toUpperCase() +
	            field.orElse("price").substring(1));
	    model.addAttribute("items", productDAO.findAll(sort));
	    return "Bai_3";
	}

}
