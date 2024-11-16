package org.example.ps27852_lab7.controller.User;

import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "user/products";
    }
    @GetMapping("/details/{id}")
    public String product(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "user/details";
    }
}
