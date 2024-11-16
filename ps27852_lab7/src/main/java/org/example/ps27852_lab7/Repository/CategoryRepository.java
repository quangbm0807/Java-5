package org.example.ps27852_lab7.Repository;

import org.example.ps27852_lab7.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Transactional
    @Modifying
    @Query("UPDATE Category c SET c.name = ?1 WHERE c.id = ?2")
    void updateCategoryName(String name, String id);

}
