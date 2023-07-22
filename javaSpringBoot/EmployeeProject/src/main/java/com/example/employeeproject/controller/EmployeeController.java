package com.example.employeeproject.controller;

import com.example.employeeproject.model.Employee;
import com.example.employeeproject.projection.EmployeeProjection;
import com.example.employeeproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getAll")
    public List<EmployeeProjection> getAllNameAndEmail(){
        return employeeService.getNameAndEmail();
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        System.out.println(employee);
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @PostMapping("/getEmployee")
    public ResponseEntity<List<Employee>> findByNameEmailAndAge(@RequestParam(value = "employeeName")String employeeName,
                                                                @RequestParam(value = "email")String email,
                                                                @RequestParam(value = "age") Optional<Integer> age){
        return ResponseEntity.ok(employeeService.findByNameEmailAndAge(employeeName,email,age));
    }
}
