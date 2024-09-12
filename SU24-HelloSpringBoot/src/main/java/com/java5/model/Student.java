package com.java5.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private String id;
	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String email;
	public Student(String id) {
		super();
		this.id = id;
	}
	
}
