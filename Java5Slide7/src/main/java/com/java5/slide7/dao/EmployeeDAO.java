package com.java5.slide7.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java5.slide7.entity.Department;
import com.java5.slide7.entity.DepartmentEmployeeSalaryReport;
import com.java5.slide7.entity.Employee;
import com.java5.slide7.entity.Gender;
@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long>  {
	@Query("SELECT e from Employee e where e.firstName like '%an%'")
	List<Employee> getEmployeeFirstNameAn();
	
	@Query("SELECT e from Employee e where e.gender =?1")
	List<Employee> getEmployeeByGenderParameter(Gender gender);
	
	@Query("SELECT e from Employee e where e.department=:dept")
	List<Employee> getEmployeeByDepartmentParameter(@Param("dept") Department dept);
	
	@Query("SELECT e from Employee e where e.salary between ?1 and ?2")
	List<Employee> findEmployeeBySalary(Double min, Double max);
	
	@Query(value = "SELECT COUNT(*) FROM Employees e WHERE e.salary BETWEEN ?1 AND ?2 ", nativeQuery = true)
	Long countEmployeeBySalary(Double min, Double max);
	
	//@NameQuery
	@Query(name="findByFirstNameAndLastName")
	List<Employee> findEmployeeByFirstNameOrLastName(String firstName, String lastName);
	
	//Sort & paging
	@Query("SELECT e from Employee e where e.salary > ?1 ORDER BY e.salary DESC")
	List<Employee> findEmployeeBySalaryGreaterDESC(Double salary);
	
	@Query("SELECT e from Employee e where e.salary > ?1")
	List<Employee> findEmployeeBySalaryGreaterSort(Double salary, Sort sort);
	
	@Query("SELECT e from Employee e where e.salary > ?1")
	List<Employee> findEmployeeBySalaryGreaterPaging(Double salary, Pageable pageable);
	
	//Report
	@Query("SELECT new com.java5.slide7.entity.DepartmentEmployeeSalaryReport(e.department, sum(e.salary)) from Employee"
			+ "	 e group by e.department ORDER BY e.department")
	List<DepartmentEmployeeSalaryReport> getTotalSalaryDepartment();
	
	//Query DSL (Domain Specific Language)
	List<Employee> findBySalaryBetween(Double min, Double max);
	
	List<Employee> findBySalary(Double salary);
	
	List<Employee> findByDepartment(Department department);
	
}
