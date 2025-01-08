package com.trots.oxtest.notification;

public interface NotificationService {

    void notifyUser(String userId, String message, String destination);

    void notifyAllUsers(String message, String destination);
}
