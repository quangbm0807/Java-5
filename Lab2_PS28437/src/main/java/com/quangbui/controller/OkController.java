package com.quangbui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ok")
public class OkController {

    // Xử lý đường dẫn /ok
    @GetMapping
    public String ok() {
        return "ok"; // Trả về view "ok"
    }

    // Xử lý đường dẫn /ok/1
    @GetMapping("/1")
    public String handleOk1(Model model) {
        model.addAttribute("result", "ok1");
        return "ok";
    }

    // Xử lý đường dẫn /ok/2
    @GetMapping("/2")
    public String handleOk2(Model model) {
        model.addAttribute("result", "ok2");
        return "ok";
    }

    // Xử lý đường dẫn /ok/3
    @GetMapping("/3")
    public String handleOk3(Model model) {
        model.addAttribute("result", "ok3");
        return "ok";
    }
}
