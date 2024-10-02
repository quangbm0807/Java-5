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
@Table(name = "Categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
	@Id
	private String Id;
	private String Name;

	@OneToMany(mappedBy = "categories")
	List<Products> products;
}
