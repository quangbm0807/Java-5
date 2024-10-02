package com.Lab_5.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Lab_5.Entity.Products;

public interface ProductDAO extends JpaRepository<Products, Integer> {

	Page<Products> findByNameContaining(String keyword, Pageable pageable);
}
