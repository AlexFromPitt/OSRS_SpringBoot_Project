package com.osrs_springboot_project.osrs_springboot_project.models;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_ACTIVITIES;
import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;

@Data
public class Player {
    private String username;

    private Skill overall;

    private Map<OSRS_SKILL, Skill> skills;

    private Map<OSRS_ACTIVITIES, Activity> activities;

    public Player() {}

    public Player(String username, Skill overall, Map<OSRS_SKILL, Skill> skills, Map<OSRS_ACTIVITIES, Activity> activities) {
        this.username = username;
        this.overall = overall;
        this.skills = skills;
        this.activities = activities;
    }

    public Player(String username, Skill[] skills, Activity[] activities) {
        OSRS_SKILL[] skillNames = OSRS_SKILL.values();
        OSRS_ACTIVITIES[] activityNames = OSRS_ACTIVITIES.values();
        
        this.skills = new LinkedHashMap<>();
        this.activities = new LinkedHashMap<>();
        this.username = username;
        this.overall = skills[0];

        for (int i = 1; i < skillNames.length; i++) {
            this.skills.put(skillNames[i], skills[i]);
        }

        for (int i = 0; i < activityNames.length; i++) {
            this.activities.put(activityNames[i], activities[i]);
        }
    }

    public Skill getSkill(OSRS_SKILL skillName){
        return this.skills.get(skillName);
    }

    public Activity getActivity(OSRS_ACTIVITIES activityName){
        return this.activities.get(activityName);
    }
}
