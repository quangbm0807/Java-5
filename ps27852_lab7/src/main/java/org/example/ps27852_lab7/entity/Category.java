package org.example.ps27852_lab7.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @Column(length = 4)
    @NotBlank(message = "Mã danh mục không được bỏ trống")
    private String id;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Tên danh mục không được bỏ trống")
    @Size(min = 3, max = 50, message = "Tên danh mục phải có độ dài từ 3 đến 50 ký tự")
    private String name;


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Product> products;

}