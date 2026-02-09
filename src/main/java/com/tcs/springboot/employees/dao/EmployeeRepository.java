package com.tcs.springboot.employees.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.springboot.employees.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // that's it ... no need to write any code LOL!

}
