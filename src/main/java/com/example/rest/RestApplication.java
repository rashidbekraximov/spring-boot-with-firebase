package com.example.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import com.example.rest.config.OpenApiProperties;
import com.example.rest.config.ServerProperties;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ClassPathResource;

@EnableConfigurationProperties({
        OpenApiProperties.class,
        ServerProperties.class
})


@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(RestApplication.class, args);

        ClassLoader classLoader = RestApplication.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccount.json")).getFile());
        InputStream is =  new ClassPathResource("serviceAccount.json").getInputStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(is))
                .build();

        FirebaseApp.initializeApp(options);



    }

}
