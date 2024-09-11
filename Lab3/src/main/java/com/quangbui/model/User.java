package com.quangbui.model;

public class User {
	private String username;
	private String password;
	private String fullName;
	private int age;
	private boolean gender;

	public User() {
		super();
	}

	public User(String userName, String password, String fullName, int age, boolean gender) {
		this.username = userName;
		this.password = password;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
	}

	 public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

}
