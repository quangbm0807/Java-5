package com.quangbui.service;

import com.quangbui.model.Product;
import com.quangbui.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product getProductById(Integer id) {
        return productsRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public void saveProduct(Product product) {
        productsRepository.save(product);
    }

    public void updateProduct(Product product) {
        productsRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productsRepository.deleteById(id);
    }
}
