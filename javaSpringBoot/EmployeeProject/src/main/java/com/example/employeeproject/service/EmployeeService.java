package com.example.employeeproject.service;

import com.example.employeeproject.model.Employee;
import com.example.employeeproject.projection.EmployeeProjection;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


public interface EmployeeService {
     List<EmployeeProjection> getNameAndEmail();

     Employee createEmployee(Employee employee);

    List<Employee> findByNameEmailAndAge(String employeeName, String email, Optional<Integer> age);
}
