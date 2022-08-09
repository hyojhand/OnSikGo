package com.ssafy.onsikgo.firebase;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class NotificationService {

    private final FCMService fcmService;
    private final Map<Long, String> tokenMap = new HashMap<>();

    public NotificationService(final FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public void register(final Long userId, final String token) {
        tokenMap.put(userId, token);
    }

    public void deleteToken(final Long userId) {
        tokenMap.remove(userId);
    }

    public String getToken(final Long userId) {
        return tokenMap.get(userId);
    }

    public void sendNotification(final NotificationRequest request) {
        try {
            fcmService.send(request);
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
    }
}
