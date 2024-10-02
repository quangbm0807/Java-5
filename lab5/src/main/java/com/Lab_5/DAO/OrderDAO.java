package com.Lab_5.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Lab_5.Entity.Orders;

public interface OrderDAO extends JpaRepository<Orders, Long> {

}
