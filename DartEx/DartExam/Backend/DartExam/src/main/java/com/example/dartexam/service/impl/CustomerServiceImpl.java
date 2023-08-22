package com.example.dartexam.service.impl;

import com.example.dartexam.entity.CustomerEntity;
import com.example.dartexam.repository.CustomerRepository;
import com.example.dartexam.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<CustomerEntity> getAllCustomer(){
        List<CustomerEntity> customerEntities = new ArrayList<>();
        try{
            customerEntities = customerRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return customerEntities;
    }

    @Override
    public CustomerEntity addCustomer( CustomerEntity customerEntity){
        try {
            customerRepository.save(customerEntity);
            return customerEntity;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
