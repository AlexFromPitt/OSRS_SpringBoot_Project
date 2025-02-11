package com.osrs_springboot_project.osrs_springboot_project.exceptions;


public class SkillNotFoundException extends RuntimeException {
    public SkillNotFoundException(String username, String skillName) {
        super("Could not find " + skillName + " for user: " + username);
    }
}
