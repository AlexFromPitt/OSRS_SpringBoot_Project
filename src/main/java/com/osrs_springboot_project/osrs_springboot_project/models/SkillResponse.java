package com.osrs_springboot_project.osrs_springboot_project.models;

import lombok.Data;

@Data
public class SkillResponse {
    private String username;
    private Skill skill;

    public SkillResponse(String username, Skill skill) {
        this.username = username;
        this.skill = skill;
    }
}
