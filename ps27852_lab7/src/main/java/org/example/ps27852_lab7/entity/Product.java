package org.example.ps27852_lab7.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
// ...
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Tên sản phẩm không được để trống.")
    @Size(max = 50, message = "Tên sản phẩm không được vượt quá 50 ký tự.")
    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200)
    private String image;

    @NotNull(message = "Giá không được để trống.")
    @Positive(message = "Không được nhập chữ")
    private Double price;

    @NotNull(message = "Ngày tạo không được để trống.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Thuộc tính mở rộng
    @Column(length = 50)
    private String cpu;

    @Column(length = 20)
    private String ram;

    @Column(length = 50)
    private String storage;

    @Column(length = 50)
    private String display;

    @Column(length = 50)
    private String os;

    @Column(length = 20)
    private String battery;
}
    