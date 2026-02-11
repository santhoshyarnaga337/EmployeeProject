package com.tcs.springboot.employees.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tcs.springboot.employees.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE e.lastName = :lastName")
	List<Employee> findByLastName(@Param("lastName") String lastName);

}
