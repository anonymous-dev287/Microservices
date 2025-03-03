package com.finflow.notificationservice.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finflow.notificationservice.dto.NotificationRequest;
import com.finflow.notificationservice.service.NotificationService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionEventConsumer {
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "transaction-events", groupId = "notification-group")
    public void consumeTransactionEvent(String message) throws MessagingException {
//        notificationService.sendEmail(request);
    	try {
    		NotificationRequest request = objectMapper.readValue(message, NotificationRequest.class);
    		System.out.println("Received transaction event: " + request.toString());
    		notificationService.sendEmail(request);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
}

