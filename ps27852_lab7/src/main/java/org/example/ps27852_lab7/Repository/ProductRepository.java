package org.example.ps27852_lab7.Repository;

import org.example.ps27852_lab7.entity.Product;
import org.example.ps27852_lab7.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(String categoryId);

    @Query("SELECT o FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    Page<Product> findByPrice(double minPrice, double maxPrice, Pageable pageable);

    @Query("SELECT o FROM Product o WHERE o.name LIKE CONCAT('%', ?1, '%')")
    Page<Product> findByKeywords(String keywords, Pageable pageable);


    @Query("SELECT new Report (o.category.name, sum(o.price), count(o)) "
            + " FROM Product o "
            + " GROUP BY o.category.name")
    List<Report> getInventoryByCategory(Pageable pageable);

    @Query("SELECT new Report (o.category.name, sum(o.price), count(o)) "
            + "FROM Product o "
            + "WHERE o.category.id = ?1 " // Thêm điều kiện theo ID danh mục
            + "GROUP BY o.category.name "
            + "ORDER BY sum(o.price) DESC")
    List<Report> findProductByCategoryId(String categoryId, Pageable pageable);
}