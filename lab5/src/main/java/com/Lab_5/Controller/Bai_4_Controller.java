package com.Lab_5.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;
import com.Lab_5.DAO.ProductDAO;
import com.Lab_5.Entity.Products;

@Controller
public class Bai_4_Controller {
	@Autowired
	private ProductDAO productDAO = null;

	@GetMapping("/page")
	private String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 3);
		Page<Products> page = productDAO.findAll(pageable);
		model.addAttribute("page", page);
		return "Bai_4";
	}
}
