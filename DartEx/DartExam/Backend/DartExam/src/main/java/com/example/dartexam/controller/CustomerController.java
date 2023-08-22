package com.example.dartexam.controller;

import com.example.dartexam.entity.CustomerEntity;
import com.example.dartexam.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerEntity>> getAllCustomer(){
        try {
            List<CustomerEntity> customerEntities = customerService.getAllCustomer();
            if(customerEntities.size() > 0) {
                return ResponseEntity.ok(customerEntities);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerEntity> addCustomer(@RequestBody CustomerEntity customerEntity){
        try {
            if(customerService.addCustomer(customerEntity) != null) {
                return ResponseEntity.ok(customerService.addCustomer(customerEntity));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
