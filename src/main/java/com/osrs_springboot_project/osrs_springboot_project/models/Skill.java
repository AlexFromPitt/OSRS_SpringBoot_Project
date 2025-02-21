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
        return "Name: " + this.skillName + " Rank: " + this.rank + " Level: " + this.level + " XP: " + this.xp;
    }

    public static class SkillComparator implements Comparator<Skill> {
        @Override
        public int compare(Skill a1, Skill a2) {
            int rank1 = a1.rank;
            int rank2 = a2.rank;
            int xp1 = a1.xp;
            int xp2 = a2.xp;
    
            if (rank1 == -1 && rank2 == -1) {
                return Integer.compare(xp2, xp1);
            }
    
            if (rank1 == -1) return 1;
            if (rank2 == -1) return -1;
    
            return Integer.compare(rank1, rank2);
        }
    }
}
