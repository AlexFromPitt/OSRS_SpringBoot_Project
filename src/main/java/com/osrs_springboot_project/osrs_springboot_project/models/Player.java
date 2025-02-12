package com.osrs_springboot_project.osrs_springboot_project.models;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;

@Data
@Document(collection = "Players")
public class Player {
    @Id
    private String username;

    private Skill overall;

    @Field("skills")
    private Map<OSRS_SKILL, Skill> skills;

    public Player() {}

    public Player(String username, Skill[] skills) {
        this.skills = new LinkedHashMap<>();
        OSRS_SKILL[] skillNames = OSRS_SKILL.values();

        this.username = username;
        this.overall = skills[0];
        for (int i = 1; i < skillNames.length; i++) {
            this.skills.put(skillNames[i], skills[i]);
        }
    }

    public Skill getSkill(OSRS_SKILL skillName){
        return this.skills.get(skillName);
    }
}
