package org.example.ps27852_lab7.Service;

import org.example.ps27852_lab7.entity.Product;
import org.example.ps27852_lab7.entity.Report;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Page<Product> getAllProducts(int page, int size);
    Page<Product> getAllProducts(int page, int size, String sortBy);
    Product getProduct(Integer id);
    void addProduct(Product product);
    void deleteProduct(Integer id);
    Page<Product> findByPrice(Double min, Double max, int page, int size, String sortBy);
    Page<Product> findByKeyword(String keyword, int page, int size, String sortBy);
    List<Report> findInventoryByCategory(int page, int size, String sortBy);
    List<Report> findProductByCategory(String category, int page, int size, String sortBy);
}
