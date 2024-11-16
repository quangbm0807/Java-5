package com.quangbui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quangbui.model.Product;
import com.quangbui.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findByNameContaining(String keyword, Pageable pageable) {
        return productRepository.findByNameContaining(keyword, pageable);
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
    public Page<Product> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    }
    
    public Page<Product> findByPriceGreaterThanEqual(Double minPrice, Pageable pageable) {
        return productRepository.findByPriceGreaterThanEqual(minPrice, pageable);
    }
    
    public Page<Product> findByPriceLessThanEqual(Double maxPrice, Pageable pageable) {
        return productRepository.findByPriceLessThanEqual(maxPrice, pageable);
    }
    
    public Page<Product> findByNameContainingAndPriceBetween(String keyword, Double minPrice, Double maxPrice, Pageable pageable) {
        return productRepository.findByNameContainingAndPriceBetween(keyword, minPrice, maxPrice, pageable);
    }
    
    public Page<Product> findByNameContainingAndPriceGreaterThanEqual(String keyword, Double minPrice, Pageable pageable) {
        return productRepository.findByNameContainingAndPriceGreaterThanEqual(keyword, minPrice, pageable);
    }
    
    public Page<Product> findByNameContainingAndPriceLessThanEqual(String keyword, Double maxPrice, Pageable pageable) {
        return productRepository.findByNameContainingAndPriceLessThanEqual(keyword, maxPrice, pageable);
    }
}