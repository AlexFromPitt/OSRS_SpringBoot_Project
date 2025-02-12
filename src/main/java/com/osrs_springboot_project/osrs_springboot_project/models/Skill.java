package com.osrs_springboot_project.osrs_springboot_project.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

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

    public static class SkillComparator implements Comparator<Skill> {
        @Override
        public int compare(Skill s1, Skill s2) {
            if (s1.level > s2.level) {
                return 1;
            } else if (s1.level < s2.level) {
                return -1;
            }  else {
                // The Skills are the same level. Check the Experience.
                if (s1.xp > s2.xp) {
                    return 1;
                } else if (s1.xp < s2.xp) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
