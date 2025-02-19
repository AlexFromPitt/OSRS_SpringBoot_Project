package com.osrs_springboot_project.osrs_springboot_project.models;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "Usernames")
public class Username {
    @Id
    private String username;

    @Field("lastUpdate")
    private Instant lastUpdate;

    public Username() {}

    public Username(String username) {
        this.username = username;
        this.lastUpdate = Instant.now();
    }

    public Username(String username, Instant lastUpdate) {
        this.username = username;
        this.lastUpdate = lastUpdate;
    }
}
