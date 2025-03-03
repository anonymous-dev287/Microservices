package com.finflow.notificationservice.service;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.finflow.notificationservice.dto.NotificationRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final JavaMailSender mailSender;

    public void sendEmail(NotificationRequest request) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(request.getRecipientEmail());
        helper.setSubject("Transaction Alert");
        helper.setText(request.getMessage());

        mailSender.send(message);
        System.out.println("Email Sent to: " + request.getRecipientEmail());
    }
}
