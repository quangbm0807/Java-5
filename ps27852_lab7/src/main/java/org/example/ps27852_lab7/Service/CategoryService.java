package org.example.ps27852_lab7.Service;

import org.example.ps27852_lab7.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Page<Category> getAllCategories(int page, int size);
    Page<Category> getAllCategories(int page, int size,String sortBy);
    Category getCategory(String id);
    void addCategory(Category category);
    void deleteCategory(String id);
    void updateCategory(String id, Category category);
}
