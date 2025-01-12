package com.trots.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "NotificationClient", url = "http://localhost:8082", path = "/api/v_0/notifications")
public interface NotificationProxy {

    @PostMapping("/{id}")
    @MessageMapping("/user-message-{userName}")
    void sendNotificationToUser(@Payload @RequestBody String message, @DestinationVariable @PathVariable String id);

}
