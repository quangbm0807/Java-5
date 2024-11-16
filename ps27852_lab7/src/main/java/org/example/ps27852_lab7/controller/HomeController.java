package org.example.ps27852_lab7.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        model.addAttribute("content", "home.jsp"); // Đường dẫn đến file JSP cần hiển thị
        model.addAttribute("activeUri", request.getRequestURI()); // Thêm URI hiện tại
        return "index"; // Trả về view index.jsp
    }
}
