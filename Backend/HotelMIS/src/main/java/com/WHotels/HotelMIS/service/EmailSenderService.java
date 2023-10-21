package com.WHotels.HotelMIS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
         SimpleMailMessage message = new SimpleMailMessage();
         message.setFrom("rasulaahangama@gmail.com");
         message.setTo(toEmail);
         message.setText(body);
         message.setSubject(subject);
         emailSender.send(message);
    }



}
