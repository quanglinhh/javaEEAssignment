package com.example.dartexam.service;

import com.example.dartexam.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> getAllCustomer();

    CustomerEntity addCustomer(CustomerEntity customerEntity);
}
