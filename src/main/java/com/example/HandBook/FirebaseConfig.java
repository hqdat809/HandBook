package com.example.HandBook;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void init() {
        try {
            ClassPathResource serviceAccountResource = new ClassPathResource("firebase-service-account.json");
            InputStream serviceAccountStream  = serviceAccountResource.getInputStream();
//            FileInputStream serviceAccount =
//                    new FileInputStream("target/classes/firebase-service-account.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                    .setDatabaseUrl("https://sotay-74bbe-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
