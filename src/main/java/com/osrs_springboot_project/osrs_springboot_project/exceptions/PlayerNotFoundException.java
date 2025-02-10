package com.osrs_springboot_project.osrs_springboot_project.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String username) {
        super("Player with username '" + username + "' not found in the database.");
    }
}
