package com.trots.oxtest.notification.impl;

import com.trots.oxtest.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private static final String DESTINATION = "/topic/notifications";


    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void notifyUser(String userId, String message) {
        messagingTemplate.convertAndSendToUser(userId, DESTINATION, message);
    }

}
