package com.quangbui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quangbui.model.PhoneContact;
import com.quangbui.service.PhoneService;

@Controller
@RequestMapping("/phone")
public class PhoneContactController {

	@Autowired
	private PhoneService phoneService;
	
	@GetMapping("/list")
    @ResponseBody
    public List<PhoneContact> findAll() {
        return phoneService.findAll();
    }
	
	@PostMapping("/create")
	@ResponseBody
	public PhoneContact createPhoneContact(@RequestBody PhoneContact phoneContact) {
	    phoneService.add(phoneContact);
	    return phoneContact;
	}
	
	@PostMapping("/update")
	@ResponseBody
	public PhoneContact updatePhoneContact(@RequestBody PhoneContact phoneContact) {
		phoneService.update(phoneContact);
		return phoneContact;
	}
	
	@PostMapping("/delete/{id}")
	@ResponseBody
	public List<PhoneContact> deletePhoneContact(@PathVariable int id) {
		boolean a = phoneService.findByID(id);
		phoneService.deleteById(id);
		if(a == true) {
			return phoneService.findAll();
		}
		return null;
	}
}
