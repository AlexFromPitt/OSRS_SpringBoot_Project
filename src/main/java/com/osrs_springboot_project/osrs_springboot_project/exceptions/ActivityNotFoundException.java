package com.osrs_springboot_project.osrs_springboot_project.exceptions;

public class ActivityNotFoundException extends RuntimeException {
    public ActivityNotFoundException(String username, String activityName) {
        super("Could not find " + activityName + " for user: " + username);
    }

    public ActivityNotFoundException(String activityName) {
        super("Could not find " + activityName + " Activity Data.");
    }
}
