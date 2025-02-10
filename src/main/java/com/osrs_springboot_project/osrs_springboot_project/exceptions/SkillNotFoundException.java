package com.osrs_springboot_project.osrs_springboot_project.exceptions;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;

public class SkillNotFoundException extends RuntimeException {
    public SkillNotFoundException(String username, OSRS_SKILL skillName) {
        super("Could not find " + skillName + " for user: " + username);
    }
}
