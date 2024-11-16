package org.example.ps27852_lab7.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Repository.ProductRepository;
import org.example.ps27852_lab7.Service.ProductService;
import org.example.ps27852_lab7.entity.Product;
import org.example.ps27852_lab7.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<Report> findProductByCategory(String category, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return productRepository.findProductByCategoryId(category, pageable);
    }

    public Page<Product> findByPrice(Double min, Double max, int page, int size, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(field).ascending()); // Sắp xếp theo trường được chỉ định
        return productRepository.findByPrice(min, max, pageable);
    }

    @Override
    public Page<Product> findByKeyword(String keyword, int page, int size, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(field).ascending());
        return productRepository.findByKeywords(keyword, pageable);
    }

    @Override
    public List<Report> findInventoryByCategory(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page, size);
        List<Report> reports = productRepository.getInventoryByCategory(pageable);

        // Sắp xếp dựa trên trường truyền vào
        if ("totalPrice".equals(field)) {
            reports.sort(Comparator.comparing(Report::getSum).reversed());
        } else if ("totalQuantity".equals(field)) {
            reports.sort(Comparator.comparing(Report::getCount).reversed());
        }

        return reports;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> getAllProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProduct(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void addProduct(Product product) {
       try {
           productRepository.save(product);
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}
