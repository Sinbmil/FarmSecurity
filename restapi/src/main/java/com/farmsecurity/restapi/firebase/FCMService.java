package com.farmsecurity.restapi.firebase;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

@Service
public class FCMService {

    public String sendMessage(String registrationToken) throws FirebaseMessagingException {
        Message message = Message.builder()
                .setAndroidConfig(AndroidConfig.builder()
                        .setTtl(3600*1000)
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .setRestrictedPackageName("com.example.farmsecurity")
                        .setNotification(AndroidNotification.builder()
                                .setTitle("Alarm")
                                .setBody("알람.")
                                .build())
                        .build())
                .setToken(registrationToken).build();

        String response = FirebaseMessaging.getInstance().send(message);

        return response;
    }
}
