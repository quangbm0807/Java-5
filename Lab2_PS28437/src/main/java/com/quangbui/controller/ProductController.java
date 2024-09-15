package com.quangbui.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quangbui.model.Product;
import com.quangbui.service.ProductService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	private static final String UPLOAD_DIR = "src/main/resources/static/img";

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	private ServletContext servletContext;

	@GetMapping("/form")
	public String form(@RequestParam(value = "id", required = false) Integer id, Model model) {
		Product product = id != null ? productService.getProductById(id) : new Product();
		if (product == null) {
			product = new Product();
		}
		model.addAttribute("product", product);
		model.addAttribute("products", productService.getAllProducts());
		return "product/product";
	}

	@PostMapping("/save")
	public String save(@RequestParam(value = "id", required = false) Integer id, @RequestParam("name") String name,
			@RequestParam("price") String price, @RequestParam("imgFile") MultipartFile imgFile,
			RedirectAttributes redirectAttributes) {
		try {
			Product product = new Product();
			if (id != null) {
				product.setId(id);
			}
			product.setName(name);
			product.setPrice(Double.parseDouble(price));

			if (!imgFile.isEmpty()) {
				String projectDir = System.getProperty("user.dir");
				String uploadPath = projectDir + "/src/main/resources/static/img/";
				File uploadDir = new File(uploadPath);

				if (!uploadDir.exists()) {
					if (!uploadDir.mkdirs()) {
						throw new IOException("Could not create upload directory: " + uploadPath);
					}
				}
				String fileName = System.currentTimeMillis() + "_" + imgFile.getOriginalFilename();
				Path filePath = Paths.get(uploadPath, fileName);
				Files.copy(imgFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
				product.setImg(fileName);
			}
			if (id == null) {
				productService.saveProduct(product);
				redirectAttributes.addFlashAttribute("message", "Product added successfully.");
			} else {
				productService.updateProduct(product);
				redirectAttributes.addFlashAttribute("message", "Product updated successfully.");
			}
		} catch (NumberFormatException e) {
			redirectAttributes.addFlashAttribute("error", "Invalid price format. Please enter a valid number.");
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("error", "Error uploading file: " + e.getMessage());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "An unexpected error occurred: " + e.getMessage());
		}
		return "redirect:/product/form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
		productService.deleteProduct(id);
		redirectAttributes.addFlashAttribute("message", "Product deleted successfully.");
		return "redirect:/product/form";
	}
}
