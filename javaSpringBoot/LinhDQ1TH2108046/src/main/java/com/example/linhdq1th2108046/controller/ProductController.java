package com.example.linhdq1th2108046.controller;

import com.example.linhdq1th2108046.model.Product;
import com.example.linhdq1th2108046.repository.ProductRepository;
import com.example.linhdq1th2108046.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/createProduct")
    private String createProduct(@ModelAttribute Product product){
        try {
            productService.createProduct(product);
            return "redirect:/listProduct";
        }catch (Exception ex){
            System.out.println("createProduct error with: "+ex);
        }
        return null;
    }

    @GetMapping("/addProduct")
    public String addEmployee(Model model){
        Product newProduct = new Product();
        model.addAttribute("product",newProduct);
        return "addProduct";
    }

    @GetMapping(value = {"/listProduct","/"})
    public String findAllEmployees(Model model){
        try {
            List<Product> products = productService.findAllProduct();
            model.addAttribute("products",products);
            return "listProduct";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
