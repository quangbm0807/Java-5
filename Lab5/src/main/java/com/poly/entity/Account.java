package com.poly.entity;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Accounts")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
	@Id
	String username;
	String password;
	String fullname;
	String email;
	String photo;
	boolean activated;
	boolean admin;
	
	@JsonIgnore
	@OneToMany (mappedBy = "account")
	List<Order> orders;
}