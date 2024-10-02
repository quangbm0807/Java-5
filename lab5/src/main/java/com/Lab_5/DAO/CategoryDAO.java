package com.Lab_5.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Lab_5.Entity.Categories;

public interface CategoryDAO extends JpaRepository<Categories, String> {

}
