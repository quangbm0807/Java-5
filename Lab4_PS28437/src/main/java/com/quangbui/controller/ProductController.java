package com.quangbui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String listProducts(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "3") int size,
                               @RequestParam(defaultValue = "id") String sort,
                               @RequestParam(defaultValue = "asc") String dir,
                               @RequestParam(required = false) String keyword) {
        
        Sort.Direction direction = Sort.Direction.fromString(dir);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        
        Page<Product> productPage;
        if (keyword != null && !keyword.isEmpty()) {
            productPage = productService.findByNameContaining(keyword, pageable);
        } else {
            productPage = productService.findAll(pageable);
        }

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        model.addAttribute("keyword", keyword);

        return "products/list";
    }

    @GetMapping("/list")
    public String products(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "12") int size,
                           @RequestParam(defaultValue = "id") String sort,
                           @RequestParam(defaultValue = "asc") String dir,
                           @RequestParam(required = false) String keyword) {
        
        Sort.Direction direction = Sort.Direction.fromString(dir);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        
        Page<Product> productPage;
        if (keyword != null && !keyword.isEmpty()) {
            productPage = productService.findByNameContaining(keyword, pageable);
        } else {
            productPage = productService.findAll(pageable);
        }

        model.addAttribute("cartSize", shoppingCartService.getCartSize());
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalItems", productPage.getTotalElements());
        model.addAttribute("sort", sort);
        model.addAttribute("dir", dir);
        model.addAttribute("keyword", keyword);

        return "products/category";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product, @RequestParam("image") MultipartFile file) {
        try {
            String imageUrl = googleDriveService.uploadFile(file);
            product.setImageUrl(imageUrl);
            productService.save(product);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
        }
        return "redirect:/products/management";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, @ModelAttribute Product product, @RequestParam(value = "image", required = false) MultipartFile file) {
        try {
            Product existingProduct = productService.findById(id);
            if (existingProduct != null) {
                if (file != null && !file.isEmpty()) {
                    googleDriveService.deleteFile(existingProduct.getImageUrl());
                    String imageUrl = googleDriveService.uploadFile(file);
                    existingProduct.setImageUrl(imageUrl);
                }
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                productService.save(existingProduct);
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
        }
        return "redirect:/products/management";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        Product product = productService.findById(id);
        if (product != null && product.getImageUrl() != null) {
            googleDriveService.deleteFile(product.getImageUrl());
        }
        productService.deleteById(id);
        return "redirect:/products/management";
    }
}