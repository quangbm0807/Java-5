package com.quangbui.model;

import jakarta.validation.constraints.DecimalMax;
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
public class Student {
	@NotBlank(message="Vui lòng nhập ID")
	private String id;

	@NotBlank(message = "Vui lòng nhập tên")
	private String fullName;

	@NotEmpty(message = "Vui lòng chọn sở thích")
	private String hobbie;
	
	@NotEmpty(message = "Vui lòng nhập email")
	@Email(message = "{Email.staff.email}")
	private String email;


	@NotBlank(message="Vui lòng chọn địa chỉ")
	private String address;
	
	private String imagePath;

	@NotNull(message = "Vui lòng chọn giới tính")
	private boolean gender;

	@DecimalMin(value = "0", inclusive = true, message = "GPA không được nhỏ 0")
	@DecimalMax(value = "10", inclusive = false, message = "GPA không được lớn hơn 10")
	private double GPA;
}
