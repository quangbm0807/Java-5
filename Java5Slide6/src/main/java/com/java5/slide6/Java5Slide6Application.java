package com.java5.slide6;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.java5.slide6.dao.EmployeeDAO;
import com.java5.slide6.entity.Employee;

@SpringBootApplication
public class Java5Slide6Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Java5Slide6Application.class, args);
		
	}

}
