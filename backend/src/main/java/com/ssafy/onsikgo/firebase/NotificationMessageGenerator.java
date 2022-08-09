package com.ssafy.onsikgo.firebase;

@FunctionalInterface
public interface NotificationMessageGenerator {

    String generateNotificationMessage(User sender, User receiver);
}
