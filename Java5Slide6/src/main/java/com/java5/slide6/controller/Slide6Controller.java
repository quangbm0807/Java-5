package com.java5.slide6.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java5.slide6.dao.EmployeeDAO;
import com.java5.slide6.dao.ProductDAO;
import com.java5.slide6.entity.Category;
import com.java5.slide6.entity.Department;
import com.java5.slide6.entity.DepartmentEmployeeSalaryReport;
import com.java5.slide6.entity.Employee;
import com.java5.slide6.entity.Gender;
import com.java5.slide6.entity.Product;

@Controller
public class Slide6Controller {
	@Autowired
	EmployeeDAO employeeDao;
	@Autowired
	ProductDAO productDao;
	
	@ResponseBody
	@GetMapping("findProductBetweenPrice")
	List<Product> findProductBetweenPrice(){
		return productDao.findProductBetweenPrice(100, 150);
	}
	
	@ResponseBody
	@GetMapping("getProductGreaterThanPrice")
	List<Product> getProductGreaterThanPrice(){
		return productDao.getProductGreaterThanPrice(100);
	}

	@ResponseBody
	@GetMapping("getProductByCategoryID")
	List<Product> getProductByCategoryID(){
		return productDao.getProductByCategoryID("CPU");
	}
	
	@ResponseBody
	@GetMapping("getEmployeeFirstNameAn")
	List<Employee> getEmployeeFirstNameAn(){
		return employeeDao.getEmployeeFirstNameAn();
	}
	
	@ResponseBody
	@GetMapping("getEmployeeByGenderParameter")
	List<Employee> getEmployeeByGenderParameter(){
		return employeeDao.getEmployeeByGenderParameter(Gender.Male);
	}
	
	@ResponseBody
	@GetMapping("getEmployeeByDepartmentParameter")
	List<Employee> getEmployeeByDepartmentParameter(@RequestParam("dept") Department dept){
		return employeeDao.getEmployeeByDepartmentParameter(dept);
	}
	
	
	@ResponseBody
	@GetMapping("findEmployeeBySalary")
	List<Employee> findEmployeeBySalary(@RequestParam("min") Double min, @RequestParam("max") Double max){
		return employeeDao.findEmployeeBySalary(min, max);
	}
	
	
	@ResponseBody
	@GetMapping("countEmployeeBySalary")
	Long countEmployeeBySalary(@RequestParam("min") Double min, @RequestParam("max") Double max){
		return employeeDao.countEmployeeBySalary(min, max);
	}
	
	@ResponseBody
	@GetMapping("findEmployeeByFirstNameOrLastName")
	List<Employee> findEmployeeByFirstNameOrLastName(@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName){
		return employeeDao.findEmployeeByFirstNameOrLastName(firstName, lastName);
	}
	
	
	@ResponseBody
	@GetMapping("findEmployeeBySalaryGreaterDESC")
	List<Employee> findEmployeeBySalaryGreaterDESC(@RequestParam("salary") Double salary){
		return employeeDao.findEmployeeBySalaryGreaterDESC(salary);
	}
	
	
	@ResponseBody
	@GetMapping("findEmployeeBySalaryGreaterSort")
	List<Employee> findEmployeeBySalaryGreaterSort(@RequestParam("salary") Double salary){
		return employeeDao.findEmployeeBySalaryGreaterSort(salary, Sort.by(Direction.ASC,"firstName"));
	}
	
	
	@ResponseBody
	@GetMapping("findEmployeeBySalaryGreaterPaging")
	List<Employee> findEmployeeBySalaryGreaterPaging(@RequestParam("salary") Double salary, 
			@RequestParam("page") Optional<Integer> page){
		PageRequest pageable = PageRequest.of(page.orElse(0), 2);
		return employeeDao.findEmployeeBySalaryGreaterPaging(salary, pageable);
	}
	
	
	@ResponseBody
	@GetMapping("getTotalSalaryDepartment")
	List<DepartmentEmployeeSalaryReport> getTotalSalaryDepartment(){ 
		return employeeDao.getTotalSalaryDepartment();
	}
	
	
	@ResponseBody
	@GetMapping("findBySalary")
	List<Employee> findBySalary(@RequestParam("salary") Double salary){
		System.out.println("findBySalary: "+ salary);
		return employeeDao.findBySalary(salary);
	}
	
	
	@ResponseBody
	@GetMapping("findBySalaryBetween")
	List<Employee> findBySalaryBetween(@RequestParam("min") Double min, @RequestParam("max") Double max){
		return employeeDao.findBySalaryBetweenOrderBySalary(min, max);
	}
	@ResponseBody
	@GetMapping("findByDepartment")
	List<Employee> findByDepartment(@RequestParam("dept") Department dept){
		return employeeDao.findByDepartment(dept);
	}
}
