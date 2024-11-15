package com.java5.slide7.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.java5.slide7.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
}