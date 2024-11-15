package com.quangbui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quangbui.service.ShoppingCartImpl;
import com.quangbui.service.ShoppingCartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ShoppingCartImpl shoppingCartService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", shoppingCartService.getCart());
        model.addAttribute("totalPrice", calculateTotalPrice());
        return "products/cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Integer id) {
        shoppingCartService.addToCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Integer id) {
        shoppingCartService.removeFromCart(id);
        return "redirect:/cart";
    }

    @PostMapping("/increase/{id}")
    public String increaseQuantity(@PathVariable Integer id) {
        shoppingCartService.increaseQuantity(id);
        return "redirect:/cart";
    }

    @PostMapping("/decrease/{id}")
    public String decreaseQuantity(@PathVariable Integer id) {
        shoppingCartService.decreaseQuantity(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        shoppingCartService.clearCart();
        return "redirect:/cart";
    }

    private double calculateTotalPrice() {
        return shoppingCartService.getCart().stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
    }
}