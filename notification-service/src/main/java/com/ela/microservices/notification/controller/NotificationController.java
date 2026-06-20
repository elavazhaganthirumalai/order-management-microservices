package com.ela.microservices.notification.controller;

import com.ela.microservices.notification.dto.NotificationRequest;
import com.ela.microservices.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public String sendNotification(
            @RequestBody NotificationRequest request) {

        service.sendNotification(request.message());
        return "Notification Sent!";
    }
}
