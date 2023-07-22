package com.example.employeeproject.service.impl;

import com.example.employeeproject.model.Employee;
import com.example.employeeproject.projection.EmployeeProjection;
import com.example.employeeproject.repository.EmployeeRepository;
import com.example.employeeproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
//    private ModelMapper mapper;

    EntityManager entityManager;

//    @PostConstruct
//    public void createInstance(){
//        mapper = new ModelMapper();
//    }

    @Override
    public List<EmployeeProjection> getNameAndEmail(){
        return employeeRepository.findAllEmployeeProjection();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findByNameEmailAndAge(String employeeName, String email, Optional<Integer> age){
        return employeeRepository.getEmployeeByNameEmailAndAge(employeeName,email,age);
    }

}
