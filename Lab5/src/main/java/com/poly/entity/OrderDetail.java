package com.poly.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Orderdetails", uniqueConstraints = { @UniqueConstraint(columnNames = { "Productid", "Orderid" }) })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Double price;
	Integer quantity;

	@ManyToOne
	@JoinColumn(name = "Productid")
	Product product;
	@ManyToOne
	@JoinColumn(name = "Orderid")
	Order order;
}