//package com.ssafy.onsikgo.firebase;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import lombok.Value;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//
//@Service
//@Slf4j
//public class FCMInitializer {
//
////    @Value("${java.file.firebase-path}")
//    @Value("${project.properties.firebase-path}")
//    private String FIREBASE_CONFIG_PATH;
//
////    private static final String FIREBASE_CONFIG_PATH = "onsikgo-firebase-adminsdk-5rss5-0fd7839e45.json";
//
//    @PostConstruct
//    public void initialize() {
//        try {
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream())).build();
//            if (FirebaseApp.getApps().isEmpty()) {
//                FirebaseApp.initializeApp(options);
//                log.info("Firebase application has been initialized");
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//}
