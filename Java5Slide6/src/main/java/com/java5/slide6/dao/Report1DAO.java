package com.java5.slide6.dao;

import com.java5.slide6.entity.Order;
import com.java5.slide6.entity.Report1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Report1DAO extends JpaRepository<Order, Long> {
    
    @Query("SELECT NEW com.java5.slide6.entity.Report1(o.account.username, o.account.fullname, " +
           "SUM(od.price * od.quantity), " +
           "SUM(CAST(od.quantity AS Long)), " +
           "MAX(o.createDate), " +
           "MIN(o.createDate)) " +
           "FROM Order o JOIN o.orderDetails od " +
           "GROUP BY o.account.username, o.account.fullname " +
           "ORDER BY SUM(od.price * od.quantity) DESC")
    List<Report1> getSalesReport();
}