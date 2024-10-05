package com.java5.slide6.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java5.slide6.entity.Account;
import com.java5.slide6.entity.Order;
import com.java5.slide6.entity.OrderDetail;
import com.java5.slide6.entity.Product;
import com.java5.slide6.entity.Report;

public interface ProductDAO extends JpaRepository<Product, Integer> {

	@Query(name = "findProductBetweenPrice")
	List<Product> findProductBetweenPrice(double minPrice, double maxPrice);

	List<Product> findAllByNameContaining(String keywords);

	@Query("SELECT new com.java5.slide6.entity.Report(p.category, SUM(p.price), COUNT(p)) " + "FROM Product p "
			+ "GROUP BY p.category " + "ORDER BY SUM(p.price) DESC")
	List<Report> getInventoryByCategory();

	@Query("SELECT od FROM OrderDetail od WHERE od.order.id IN "
			+ "(SELECT od2.order.id FROM OrderDetail od2 GROUP BY od2.order.id "
			+ "ORDER BY SUM(od2.price * od2.quantity) DESC)")
	List<OrderDetail> findOrderDetailsForHighestValueOrder();

	@Query("SELECT o FROM Order o WHERE o.id IN (SELECT od.order.id FROM OrderDetail od " + "GROUP BY od.order.id "
			+ "ORDER BY SUM(od.price * od.quantity) ASC)")
	List<Order> findLowestValueOrder();

	@Query("SELECT a FROM Account a WHERE a.username IN "
			+ "(SELECT o.account.username FROM Order o JOIN o.orderDetails od " + "GROUP BY o.account.username "
			+ "ORDER BY SUM(od.price * od.quantity) DESC)")
	List<Account> findTopCustomer();

	@Query("SELECT p FROM Product p JOIN p.orderDetails od JOIN od.order o WHERE o.createDate BETWEEN :beginDate AND :endDate "
			+ "GROUP BY p " + "ORDER BY SUM(od.quantity) DESC")
	List<Product> findTopSellingProducts(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}