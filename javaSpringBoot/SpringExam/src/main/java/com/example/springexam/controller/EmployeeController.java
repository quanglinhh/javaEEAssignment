package com.example.springexam.controller;

import com.example.springexam.model.Employee;
import com.example.springexam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public String createEmployee(@ModelAttribute Employee employee){
        try{
           employeeService.createEmployee(employee);
            return "redirect:/findAllEmployees";
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }

    @GetMapping("/addEmployee")
    public String addEmployee(Model model){
        Employee newEmployee = new Employee();
        model.addAttribute("employee",employeeService.createEmployee(newEmployee));
        return "createEmployee";
    }

    @GetMapping(value = {"/findAllEmployees","/"})
    public String findAllEmployees(Model model){
        try {
            List<Employee> employees = employeeService.findAllEmployees();
            model.addAttribute("employees",employees);
            return "listEmployees";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
