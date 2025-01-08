package com.trots.oxtest.notification.impl;

import com.trots.oxtest.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private static final String TOPIC = "/topic";
    private static final String NOTIFICATIONS = "notifications";


    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void notifyUser(String userId, String message, String destination) {
        messagingTemplate.convertAndSendToUser(userId, destination + NOTIFICATIONS, message);
    }

    @Override
    public void notifyAllUsers(String message, String destination) {
        messagingTemplate.convertAndSend(TOPIC + destination, message);
    }

}
