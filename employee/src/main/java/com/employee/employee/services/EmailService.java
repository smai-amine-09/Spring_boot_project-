package com.employee.employee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    // @Value("${SPRING_MAIL_USERNAME}")
    private String from = "mohamedsmai2023@gmail.com";
    @Value("${backend.origin}")
    private String ORIGIN;

    public void sendAccountCreationEmail(String to, String token) {
        String link = ORIGIN + "/auth/signup?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Creat Your Account");
        message.setText("Hi!  Please create your account using this link below: \n" + link);
        mailSender.send(message);
    }
}
