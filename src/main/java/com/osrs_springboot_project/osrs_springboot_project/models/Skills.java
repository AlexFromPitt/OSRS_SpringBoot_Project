package com.osrs_springboot_project.osrs_springboot_project.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;

import lombok.Data;

@Data
@Document(collection = "Skills")
public class Skills {
    @Id
    private String username;

    private Skill overall;

    @Field("skills")
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
