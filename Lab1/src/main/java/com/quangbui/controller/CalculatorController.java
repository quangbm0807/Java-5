package com.quangbui.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/calculator")
    public String showCalculator() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculateResult(@RequestParam("num1") double num1,
                                  @RequestParam("num2") double num2,
                                  @RequestParam("operation") String operation,
                                  Model model) {
        double result;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                result = num1 / num2;
                break;
            default:
                result = 0;
        }
        model.addAttribute("result", result);
        return "calculator";
    }
}