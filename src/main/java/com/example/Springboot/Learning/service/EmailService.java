package com.example.Springboot.Learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setCc(new String[] {
                "2100039095cse.r@gmail.com",
                "gpavansai21@gmail.com"
        });
        mailSender.send(message);
    }

    public void sendWelcomeEmail(String to, String name) {
        String subject = "Welcome to Our Company!";
        String message = String.format("Hello %s,\n\nWelcome to the team! We're excited to have you onboard.\n\nRegards,\nJava Demo Application", name);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
