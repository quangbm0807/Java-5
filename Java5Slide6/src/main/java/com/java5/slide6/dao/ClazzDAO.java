package com.java5.slide6.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.java5.slide6.entity.Clazz;

public interface ClazzDAO extends JpaRepository<Clazz, Long>{
	
	List<Clazz> findByNumberOfStudentsBetween(Integer min, Integer max);
	
	List<Clazz> findBySemesterContaining(String semester);
	
	
	List<Clazz> findByNameContaining(String keyword);
	
	
	List <Clazz> findByNameAndSemesterContaining(@Param("name") String name, @Param("semester") String semester);
	
}
