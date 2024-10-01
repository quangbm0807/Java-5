package com.java5.slide5.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.java5.slide5.entity.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
}