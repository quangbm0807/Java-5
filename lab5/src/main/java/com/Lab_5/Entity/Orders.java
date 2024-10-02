package com.Lab_5.Entity;

import java.util.Date;
import java.util.List;

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
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Address;

	@Temporal(TemporalType.DATE)
	private Date CreateDate = new Date();

	@ManyToOne
	@JoinColumn(name = "Username")
	private Accounts accounts;
	
	@OneToMany(mappedBy = "orders")
	List<OrderDetails> details;
}
