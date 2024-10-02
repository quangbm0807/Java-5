package com.Lab_5.Entity;

import java.util.List;

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
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
	@Id
	private String Username;
	private String Password;
	private String Fullname;
	private String Email;
	private String Photo;
	private boolean Activated;
	private boolean Admin;
	
	@OneToMany(mappedBy = "accounts")
	List<Orders> orders;
}
