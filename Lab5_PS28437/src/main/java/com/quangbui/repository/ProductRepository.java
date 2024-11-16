package com.quangbui.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quangbui.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    Page<Product> findByNameContaining(String keyword, Pageable pageable);
    
    Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);
    
    Page<Product> findByPriceGreaterThanEqual(Double minPrice, Pageable pageable);
    
    Page<Product> findByPriceLessThanEqual(Double maxPrice, Pageable pageable);
    
    Page<Product> findByNameContainingAndPriceBetween(String keyword, Double minPrice, Double maxPrice, Pageable pageable);
    
    Page<Product> findByNameContainingAndPriceGreaterThanEqual(String keyword, Double minPrice, Pageable pageable);
    
    Page<Product> findByNameContainingAndPriceLessThanEqual(String keyword, Double maxPrice, Pageable pageable);
}