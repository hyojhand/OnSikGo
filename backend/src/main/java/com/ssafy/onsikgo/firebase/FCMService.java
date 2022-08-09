package com.ssafy.onsikgo.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class FCMService {

//    @Value("${project.properties.firebase-create-scoped}")
//    String fireBaseCreateScoped;
//    private FirebaseMessaging instance;

    public void send(final NotificationRequest notificationRequest) throws InterruptedException, ExecutionException {
        Message message = Message.builder()
                .setToken(notificationRequest.getToken())
                .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
                        .setNotification(new WebpushNotification(notificationRequest.getTitle(),
                                notificationRequest.getMessage()))
                        .build())
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        log.info("Sent message: " + response);
    }

//    public void sendTargetMessage(String targetToken, String title, String body) throws FirebaseMessagingException {
//        this.sendTargetMessage(targetToken, title, body, null);
//    }
//    public void sendTargetMessage(String targetToken, String title, String body, String image) throws FirebaseMessagingException {
//        Notification notification = Notification.builder().setTitle(title).setBody(body).setImage(image).build();
//        Message msg = Message.builder().setToken(targetToken).setNotification(notification).build();
//        sendMessage(msg);
//    }
//
//    public void sendTopicMessage(String topic, String title, String body) throws FirebaseMessagingException {
//        this.sendTopicMessage(topic, title, body, null);
//    }
//    public void sendTopicMessage(String topic, String title, String body, String image) throws FirebaseMessagingException {
//        Notification notification = Notification.builder().setTitle(title).setBody(body).setImage(image).build();
//        Message msg = Message.builder().setTopic(topic).setNotification(notification).build();
//        sendMessage(msg);
//    }
//
//    public String sendMessage(Message message) throws FirebaseMessagingException {
//        return this.instance.send(message);
//    }
//
//    @PostConstruct
//    public void firebaseSetting() throws IOException {
//        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("google-fcm-...-key.json").getInputStream())
//                .createScoped((Arrays.asList(fireBaseCreateScoped)));
//
//        FirebaseOptions secondaryAppConfig = FirebaseOptions.builder()
//                .setCredentials(googleCredentials)
//                .build();
//        FirebaseApp app = FirebaseApp.initializeApp(secondaryAppConfig);
//        this.instance = FirebaseMessaging.getInstance(app);
//    }
//
//    public BatchResponse sendMessage(MulticastMessage message) throws FirebaseMessagingException {
//        return this.instance.sendMulticast(message);
//    }

}