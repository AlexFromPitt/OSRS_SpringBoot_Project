package com.osrs_springboot_project.osrs_springboot_project.models;

import java.util.List;

import lombok.Data;

@Data
public class SkillResponse {
    private String username;
    private List<Skill> skill;

    public SkillResponse(String username, List<Skill> skill) {
        this.username = username;
        this.skill = skill;
    }
}
