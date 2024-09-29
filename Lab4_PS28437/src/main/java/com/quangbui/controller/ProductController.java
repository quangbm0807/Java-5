package com.quangbui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.quangbui.model.Product;
import com.quangbui.service.GoogleDriveService;
import com.quangbui.service.ProductService;
import com.quangbui.service.ShoppingCartImpl;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ShoppingCartImpl shoppingCartService;
    @Autowired
    private GoogleDriveService googleDriveService;

    @GetMapping("/management")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }
    @GetMapping("/list")
    public String products(Model model) {
    	model.addAttribute("cartSize", shoppingCartService.getCartSize());
        model.addAttribute("products", productService.findAll());
        return "products/category";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile file) {
        try {
            String imageUrl = googleDriveService.uploadFile(file);
            product.setImageUrl(imageUrl);
            productService.save(product);
        } catch (Exception e) {
        }
        return "redirect:/products/management";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, @ModelAttribute Product product, @RequestParam(value = "image", required = false) MultipartFile file) {
        try {
            Product existingProduct = productService.findById(id);
            googleDriveService.deleteFile(existingProduct.getImageUrl());
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            
            if (file != null && !file.isEmpty()) {
                String imageUrl = googleDriveService.uploadFile(file);
                existingProduct.setImageUrl(imageUrl);
            }
            productService.save(existingProduct);
        } catch (Exception e) {
        }
        return "redirect:/products/management";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
    	Product p = new Product();
    	p = productService.findById(id);
    	if(p.getImageUrl() != null) {
    		googleDriveService.deleteFile(p.getImageUrl());
    	}
        productService.deleteById(id);
        return "redirect:/products/management";
    }
}