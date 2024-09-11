package com.poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.AccountException;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	private static final long serialVersionUID = -2666284876195292999L;

	@Id
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String photo;
	private boolean activated;
	private boolean admin;
	
	
}
