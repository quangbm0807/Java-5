package com.java5.slide3.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.java5.slide3.model.Staff2;
import com.java5.slide3.model.Country;

@Controller
public class Staff2Controller {
	@GetMapping("staff2/index")
	public String index(Model model) {
		Staff2 staff2 = new Staff2();
		staff2.setPosition("MAN");
		staff2.setCountry(new Country("US","USA"));
		model.addAttribute("staff2", staff2);
		return "staff2Form";
	}
	@PostMapping("staff2/save")
	public String save(@ModelAttribute("staff2") Staff2 staff2){
		return "staff2Form";
	}
	@ModelAttribute("positions")
	public Map<String, String> getPositions(){
		Map<String, String> positionsMap = new HashMap<>();
		positionsMap.put("CEO", "CEO");
		positionsMap.put("MAN", "Manager");
		positionsMap.put("EMP", "Employee");
		positionsMap.put("PM", "Project Manager");
		return positionsMap;
	}
	@ModelAttribute("hobbies")
	public String [] getHobbies() {
		String []hobbies = {"Football","Table tennis", "Basketball", "Playing game"};
		return hobbies;
	}
	@ModelAttribute("nationalities")
	public List<Country> getNationalities(){
		List<Country> listCountries = new ArrayList<>();
		listCountries.add(new Country("LA","Lao"));
		listCountries.add(new Country("VN","Vietnam"));
		listCountries.add(new Country("JP","Japan"));
		listCountries.add(new Country("US","USA"));
		listCountries.add(new Country("TL", "Thailand"));
		return listCountries;
	}
}
