package com.osrs_springboot_project.osrs_springboot_project.models.Player;

import java.time.Instant;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Username {
    @Id
    private String username;

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
