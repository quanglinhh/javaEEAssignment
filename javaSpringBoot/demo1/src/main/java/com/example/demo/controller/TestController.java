package com.example.demo.controller;

import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class TestController {

    @Autowired
    EmailService emailService;

    @GetMapping
    public String sendMail(){
        try {
            String to = "Linhdqth2108046@fpt.edu.vn";
            String subject = "Kết quả xét nghiệm";
            String text = "Cho bố mày gửi";
            String filePath = "src/main/resources/medical_record.pdf";
            emailService.sendEmail(to,subject,text,filePath);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Send email failed";
    }
}
