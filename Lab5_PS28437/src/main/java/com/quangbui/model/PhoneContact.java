package com.quangbui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneContact {
	private int id;
	private String name;
	private String phoneNumber;
	private String note;
	private String address;
}
