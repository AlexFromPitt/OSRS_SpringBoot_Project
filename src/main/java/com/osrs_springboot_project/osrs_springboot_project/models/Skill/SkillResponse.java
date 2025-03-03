package com.osrs_springboot_project.osrs_springboot_project.models.Skill;

import java.util.Comparator;

import lombok.Data;

@Data
public class SkillResponse {
    private String username;
    private Skill skill;

    public SkillResponse(String username, Skill skill) {
        this.username = username;
        this.skill = skill;
    }

    public static class SkillResponseComparator implements Comparator<SkillResponse> {
        @Override
        public int compare(SkillResponse a1, SkillResponse a2) {
            int rank1 = a1.skill.getRank();
            int rank2 = a2.skill.getRank();
            int xp1 = a1.skill.getXp();
            int xp2 = a2.skill.getXp();
    
            if (rank1 == -1 && rank2 == -1) {
                return Integer.compare(xp2, xp1);
            }
    
            if (rank1 == -1) return 1;
            if (rank2 == -1) return -1;
    
            return Integer.compare(rank1, rank2);
        }
    }
}
