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
@Table(name = "Categories")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
	@Id
	String id;
	String name;
	
	public Category(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@JsonIgnore
	@OneToMany (mappedBy = "category")
	List<Product> products;
	
	@Override
	public String toString() {
		return "[id=" + this.id + "; name = " + this.name + "]";
	}
}