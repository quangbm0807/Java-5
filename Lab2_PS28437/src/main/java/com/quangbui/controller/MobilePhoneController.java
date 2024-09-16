package com.quangbui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.quangbui.model.MobilePhone;
import com.quangbui.service.MobilePhoneService;

@Controller
@RequestMapping("/mobilephone")
public class MobilePhoneController {

    @Autowired
    private MobilePhoneService mobilePhoneService;

    @GetMapping
    public String listMobilePhones(Model model) {
        model.addAttribute("mobilePhones", mobilePhoneService.getAllMobilePhone());
        model.addAttribute("mobilePhone", new MobilePhone());
        return "mobilePhoneForm";
    }

    @PostMapping("/save")
    public String saveMobilePhone(@ModelAttribute MobilePhone mobilePhone) {
        mobilePhoneService.addMobilePhone(mobilePhone);
        return "redirect:/mobilephone";
    }

    @GetMapping("/edit/{index}")
    public String editMobilePhone(@PathVariable int index, Model model) {
        MobilePhone mobilePhone = mobilePhoneService.getMobilePhoneById(index);
        model.addAttribute("mobilePhone", mobilePhone);
        model.addAttribute("index", index);
        return "mobilephone-edit";
    }

    @PostMapping("/update/{index}")
    public String updateMobilePhone(@PathVariable int index, @ModelAttribute MobilePhone mobilePhone) {
        mobilePhoneService.updateMobilePhone(index, mobilePhone);
        return "redirect:/mobilephone";
    }

    @GetMapping("/delete/{index}")
    public String deleteMobilePhone(@PathVariable int index) {
        mobilePhoneService.deleteMobilePhone(index);
        return "redirect:/mobilephone";
    }
}