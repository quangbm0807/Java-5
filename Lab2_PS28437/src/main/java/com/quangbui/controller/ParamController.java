package com.quangbui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/param")
public class ParamController {

    @RequestMapping("/form")
    public String form() {
        return "form";
    }

    @RequestMapping("/save")
    public String save(@RequestParam("x") String x,
					   @RequestParam("y") String y,
					   Model model){
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        return "form";
    }
}
