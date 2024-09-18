package com.java5.slide3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
	private String id;
	private String fullName;
	private String email;
	private Double salary;
	private boolean gender;
	private String position;
	
}
