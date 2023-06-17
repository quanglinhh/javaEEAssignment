package com.example.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {
    @GetMapping("user/hello")
    public String user(Model model){
        model.addAttribute("role","USER");
        return "user";
    }
    @GetMapping("admin/hello")
    public String admin(Model model){
        model.addAttribute("role","ADMIN");
        return "admin";
    }
//    @GetMapping
//    public String login(){
//        return "login";
//    }

    @GetMapping("access-denied")
    public String accessDenied(){
        return "access-denied";
    }
}
