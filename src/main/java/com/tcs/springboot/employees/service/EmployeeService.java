package com.tcs.springboot.employees.service;

import com.tcs.springboot.employees.entity.Employee;
import com.tcs.springboot.employees.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findById(long theId);

	Employee save(EmployeeRequest employeeRequest);

	Employee update(long id, EmployeeRequest employeeRequest);

	Employee convertToEmployee(long id, EmployeeRequest employeeRequest);

	void deleteById(long theId);

	List<Employee> findByLastName(String lastName);

}
