package com.osrs_springboot_project.osrs_springboot_project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    private OSRS_SKILL skillName;
    private Integer rank;
    private Short level;
    private Integer xp;

    public String toString() {
        return "Name: " + skillName + " Rank: " + rank + " Level: " + level + " XP: " + xp;
    }
}
