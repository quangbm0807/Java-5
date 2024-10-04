package com.java5.slide6.dao;


import java.util.List;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java5.slide6.entity.Product;
import com.java5.slide6.entity.Report;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	@Query(name="findProductBetweenPrice")
	List<Product> findProductBetweenPrice(double minPrice, double maxPrice);
	
	@Query("SELECT product FROM Product product where product.price > ?1")
	List<Product> getProductGreaterThanPrice(double price);
	
	@Query(value="select p.* from products p where p.categoryid =:categoryName", nativeQuery=true)
	List<Product> getProductByCategoryID(@Param("categoryName") String categoryID);
	
	@Query("SELECT o FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
	List<Product> findByPrice(double minPrice, double maxPrice);

//	@Query("SELECT o FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
//	List<Product> findByPrice(double minPrice, double maxPrice);
	List<Product> findByPriceBetween(double minPrice, double maxPrice);

//@Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
//Page<Product> findByKeywords(String keywords, Pageable pageable);

	Page<Product> findAllByNameLike(String keywords, Pageable pageable);

	@Query("SELECT new Report(o.category, sum(o.price), count(o)) "
			+ " FROM Product o "
			+ " GROUP BY o.category"
			+ " ORDER BY sum(o.price) DESC")
	List<Report> getInventoryByCategory();
}
