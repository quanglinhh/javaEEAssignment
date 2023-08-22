package com.example.demo.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmail(String to, String subject, String text, String filePath) throws MessagingException;
}
