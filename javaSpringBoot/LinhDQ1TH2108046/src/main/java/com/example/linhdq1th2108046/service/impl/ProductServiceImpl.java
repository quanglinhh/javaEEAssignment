package com.example.linhdq1th2108046.service.impl;

import com.example.linhdq1th2108046.model.Product;
import com.example.linhdq1th2108046.repository.ProductRepository;
import com.example.linhdq1th2108046.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        try{
            product.setDateOfManf(new Date());
            return productRepository.save(product);
        }catch (Exception ex){
            ex.printStackTrace();
        }
      return null;
    }

    @Override
    public List<Product> findAllProduct() {
        try {
           return productRepository.findAll();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
