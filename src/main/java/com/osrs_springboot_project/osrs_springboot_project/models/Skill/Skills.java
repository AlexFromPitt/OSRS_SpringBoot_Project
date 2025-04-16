package com.osrs_springboot_project.osrs_springboot_project.models.Skill;

import java.util.Map;

import org.springframework.data.annotation.Id;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;

import lombok.Data;

@Data
public class Skills {
    @Id
    private String username;

    private Skill overall;

    private Map<OSRS_SKILL, Skill> skills;

    public Skills() {}

    public Skills(String username, Skill overall, Map<OSRS_SKILL, Skill> skills) {
        this.username = username;
        this.overall = overall;
        this.skills = skills;
    }

    public Skill getSkill(OSRS_SKILL skillName){
        return this.skills.get(skillName);
    }
}
