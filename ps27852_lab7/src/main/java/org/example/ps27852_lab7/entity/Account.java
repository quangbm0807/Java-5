package org.example.ps27852_lab7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @Column(length = 50)
    @NotBlank(message = "Tên người không được bỏ trống")
    private String username;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Column(length = 50, nullable = false)
    private String password;

    @NotBlank(message = "Họ và tên không được để trống")
    @Column(length = 50, nullable = false)
    private String fullname;

    @NotBlank(message = "Email không được bỏ trống")
    @Email(message = "Email không hợp lệ")
    @Column(length = 50)
    private String email;

    @NotBlank(message = "SĐT không được bỏ trống")
    @Size(min = 10, max = 10, message = "SDT phải có độ dài 10 ký tự")
    @Column(length = 10)
    private String phone;

    @Column(length = 50)
    private String photo;

    private boolean activated;

    private boolean admin;

    @OneToMany(mappedBy = "account")
    private java.util.List<Order> orders;
}
