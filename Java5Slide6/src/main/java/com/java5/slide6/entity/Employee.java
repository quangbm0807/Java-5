package com.java5.slide6.entity;
import java.io.Serializable;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import lombok.Data;
@NamedQuery(name="findByFirstNameAndLastName", 
			query="SELECT e from Employee e where e.firstName like ?1 or e.lastName like ?2")
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Employees")
public class Employee  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String lastName;
	String firstName;
	@Enumerated(EnumType.ORDINAL)
	Gender gender;
	String email;
	Double salary;
	@Enumerated(EnumType.ORDINAL)
	Department department;
}