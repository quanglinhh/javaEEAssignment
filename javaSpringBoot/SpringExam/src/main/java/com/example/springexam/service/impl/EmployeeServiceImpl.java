package com.example.springexam.service.impl;

import com.example.springexam.model.Employee;
import com.example.springexam.repository.EmployeeRepository;
import com.example.springexam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employee) {
        try{
            employeeRepository.save(employee);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findAllEmployees() {
        try{
            return  employeeRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
      return null;
    }
}
