package com.osrs_springboot_project.osrs_springboot_project;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MongoDBConnectionVerifier {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @PostConstruct
    public void verifyMongoUri() {
        System.out.println("########################################################MongoDB URI from env: " + mongoUri);
    }
}