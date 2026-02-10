package com.tcs.springboot.employees.service;

import com.tcs.springboot.employees.dao.EmployeeRepository;
import com.tcs.springboot.employees.entity.Employee;
import com.tcs.springboot.employees.request.EmployeeRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeRequest request;

    @BeforeEach
    void setup() {
        employee = new Employee(1L, "John", "Doe", "john@test.com");
        request = new EmployeeRequest(null, null, null);
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john@test.com");
    }

    // ✅ findAll
    @Test
    void testFindAll() {
        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        List<Employee> list = employeeService.findAll();

        assertEquals(1, list.size());
        verify(employeeRepository).findAll();
    }

    // ✅ findById success
    @Test
    void testFindByIdFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.findById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    // ✅ findById not found
    @Test
    void testFindByIdNotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> employeeService.findById(1L));
    }

    // ✅ save
    @Test
    void testSave() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee saved = employeeService.save(request);

        assertNotNull(saved);
        verify(employeeRepository).save(any(Employee.class));
    }

    // ✅ update
    @Test
    void testUpdate() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee updated = employeeService.update(1L, request);

        assertEquals("John", updated.getFirstName());
        verify(employeeRepository).save(any(Employee.class));
    }

    // ✅ delete
    @Test
    void testDeleteById() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteById(1L);

        verify(employeeRepository).deleteById(1L);
    }

    // ✅ convertToEmployee
    @Test
    void testConvertToEmployee() {
        Employee e = employeeService.convertToEmployee(5L, request);

        assertEquals(5L, e.getId());
        assertEquals("John", e.getFirstName());
    }
}