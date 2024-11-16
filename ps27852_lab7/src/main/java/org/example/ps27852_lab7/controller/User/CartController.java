package org.example.ps27852_lab7.controller.User;

import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class CartController {
    private final ItemService itemService;


    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("totalPrice", itemService.totalPrice());
        model.addAttribute("products", itemService.getAllItems()); // Truyền danh sách items
        return "user/Cart";
    }

    @GetMapping("/saveCart/{id}")
    public String saveCart(Model model, @PathVariable("id") Integer id) {
        itemService.createItem(id);
        return "redirect:/user/cart";
    }
    @GetMapping("/deleteCart/{id}")
    public String deleteCart(Model model, @PathVariable("id") Integer id) {
        itemService.deleteItemById(id);
        return "redirect:/user/cart";
    }
    @PostMapping("/updateCart/{id}")
    public String updateCartItem(@PathVariable("id") Integer id, @RequestParam("quantity") int qty) {
        if (qty > 0) {
            itemService.updateItem(id, qty);
        }
        else {
            itemService.deleteItemById(id);
        }
        return "redirect:/user/cart"; // Sau khi cập nhật, chuyển hướng về trang giỏ hàng
    }
    @GetMapping("/checkout")
    public String thanhToan(Model model) {

        return "redirect:/user/cart";
    }
}
