package com.example.springexam.controller;

import com.example.springexam.model.Employee;
import com.example.springexam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        try{
            Employee newEmployee = employeeService.createEmployee(employee);
            return ResponseEntity.ok(newEmployee);
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }

    @GetMapping("/findAllEmployees")
    public ResponseEntity<List<Employee>> findAllEmployees(){
        try {
            List<Employee> employees = employeeService.findAllEmployees();
            if(employees.size()==0){
                return (ResponseEntity<List<Employee>>) ResponseEntity.notFound();
            }
            return ResponseEntity.ok(employees);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
