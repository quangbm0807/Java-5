package com.java5.slide3.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff3 {
	
	@NotBlank(message="Please enter staff's ID")
	private String id;
	@NotBlank(message = "{NotEmpty.staff.fullName}")
	private String fullName;
	@NotEmpty(message = "{NotEmpty.staff.email}")
	@Email(message = "{Email.staff.email}")
	private String email;
	@NotNull(message = "{NotEmpty.staff.salary}")
	@DecimalMin(message = "Lương phải lớn hơn 9.5$",value = "9.5")
	private Double salary;
	@NotNull
	private boolean gender;
	@NotEmpty
	private String position;
	
}