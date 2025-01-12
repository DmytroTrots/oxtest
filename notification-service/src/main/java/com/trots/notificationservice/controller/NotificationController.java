package com.trots.notificationservice.controller;

import com.trots.notificationservice.service.NotificationService;
import com.trots.proxy.NotificationProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v_0/notifications")
@RequiredArgsConstructor
public class NotificationController implements NotificationProxy {

    private final NotificationService notificationService;

    @Override
    public void sendNotificationToUser(String message, String id) {
        notificationService.notifyUser(id, message);
    }
}
