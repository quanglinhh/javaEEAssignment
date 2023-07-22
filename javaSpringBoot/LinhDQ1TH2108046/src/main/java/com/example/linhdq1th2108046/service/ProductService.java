package com.example.linhdq1th2108046.service;

import com.example.linhdq1th2108046.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    List<Product> findAllProduct();

}
