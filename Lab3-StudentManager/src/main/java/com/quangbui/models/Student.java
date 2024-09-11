package com.quangbui.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private int id;
	private String name;
	private float mark;
	private Major major;

	public String toString()
	{
		return "Student [id=" + id + ", name=" + name + ", mark=" + mark + ", major=" + major.toString() + "]";
	}
}
