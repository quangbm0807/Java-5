package com.Lab_5.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Lab_5.Entity.OrderDetails;

public interface OrderDetailDAO extends JpaRepository<OrderDetails, Long>{

}
