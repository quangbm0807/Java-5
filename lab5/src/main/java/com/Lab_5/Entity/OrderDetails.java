package com.Lab_5.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderDetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private double Price;
	private int Quantity;

	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Products products;

    @ManyToOne
    @JoinColumn(name = "OrderId")
	private Orders orders;
}
