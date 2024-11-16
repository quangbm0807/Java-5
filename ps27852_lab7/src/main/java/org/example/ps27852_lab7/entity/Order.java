package org.example.ps27852_lab7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Account account;  // Thay thế String username bằng Account

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(length = 100)
    private String code;

    @Column(length = 100)
    private String status;

    @OneToMany(mappedBy = "order")
    private java.util.List<OrderDetail> orderDetails;
}
