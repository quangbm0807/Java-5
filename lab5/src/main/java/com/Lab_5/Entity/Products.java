package com.Lab_5.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "Price")
	private double price;

	@Column(name = "Creationdate")
	@Temporal(TemporalType.DATE)
	private Date creationdate = new Date();
	
	@Column(name = "Available")
	private boolean available;

	@ManyToOne
	@JoinColumn(name = "CategoryID")
	private Categories categories;

	@OneToMany(mappedBy = "products")
	List<OrderDetails> orderDetails;
}
