package com.osrs_springboot_project.osrs_springboot_project.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String username) {
        super("Unable to find Player data for user: '" + username);
    }
}
