package com.quangbui.model;

import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobilePhone {
	private String name;
	private Double price;
	private String maker;
	private String img;
	private String country;
}
