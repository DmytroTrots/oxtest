package com.trots.notificationservice.service.impl;

import com.trots.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private static final String QUEUE_NAME = "/queue/notifications-";


    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void notifyUser(String username, String message) {
        messagingTemplate.convertAndSend(QUEUE_NAME + username, message);
    }

}
