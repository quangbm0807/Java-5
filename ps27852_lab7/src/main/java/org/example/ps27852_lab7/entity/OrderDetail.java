package org.example.ps27852_lab7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "order_Id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_Id", nullable = false)
    private Product product;

    private Double price;

    private int quantity;
}
