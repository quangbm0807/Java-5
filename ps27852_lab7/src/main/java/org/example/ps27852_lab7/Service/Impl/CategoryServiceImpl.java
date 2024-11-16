package org.example.ps27852_lab7.Service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.ps27852_lab7.Repository.CategoryRepository;
import org.example.ps27852_lab7.Repository.ProductRepository;
import org.example.ps27852_lab7.Service.CategoryService;
import org.example.ps27852_lab7.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAllCategories(int page, int size) {
        // Tạo Pageable object để xác định page và size
        Pageable pageable = PageRequest.of(page, size);

        // Sử dụng repository để trả về Page<Account>
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> getAllCategories(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy));
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category getCategory(String id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(String id) {
        // Tìm danh mục theo ID
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
// Cập nhật category cho các sản phẩm liên quan
//        List<Product> products = productRepository.findByCategoryId(id);
//        for (Product product : products) {
//            product.setCategory(null); // Hoặc gán category mới
//        }
//        productRepository.saveAll(products); // Lưu lại các sản phẩm đã cập nhật
        categoryRepository.deleteById(id); // Xóa danh mục
    }

    @Override
    public void updateCategory(String id, Category category) {
        categoryRepository.updateCategoryName(category.getName(), id);
    }
}