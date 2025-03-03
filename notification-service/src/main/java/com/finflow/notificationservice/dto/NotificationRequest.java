package com.finflow.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequest {
    private String recipientEmail;
    private String message;
    
    @Override
    public String toString() {
        return "NotificationRequest{" +
 
               ", message='" + message + '\'' +
               ", recipient='" + recipientEmail + '\'' +
               '}';
    }
}
