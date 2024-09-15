package com.quangbui.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quangbui.model.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByName(String name);
}
