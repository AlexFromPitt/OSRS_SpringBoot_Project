package com.osrs_springboot_project.osrs_springboot_project.services;

import com.osrs_springboot_project.osrs_springboot_project.controllers.ActivityController;
import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill.SkillResponse;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill.Skills;
import com.osrs_springboot_project.osrs_springboot_project.repositories.SkillRepository;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    private final ActivityController activityController;

    @Autowired
    SkillRepository skillRepository;

    SkillService(ActivityController activityController) {
        this.activityController = activityController;
    }

    public List<SkillResponse> getSkillRanks(String skillName) {
        List<Skills> skillsList = this.skillRepository.findAll();
        List<SkillResponse> skillRanks = new ArrayList<>();

        for (Skills skills : skillsList) {
            skillRanks.add(new SkillResponse(
                skills.getUsername(), 
                skills.getSkill(OSRS_SKILL.valueOf(skillName.toUpperCase()))));
        }
        skillRanks.sort(new SkillResponse.SkillResponseComparator());
        
        return skillRanks;
    }

    public List<SkillResponse> getTopSkillRanks(String skillName, Integer topNumber) {
        List<SkillResponse> skillRanks = this.getSkillRanks(skillName);

        return skillRanks.subList(0, topNumber);
    }

    public List<SkillResponse> getSkillRanksGreaterThanLevel(String skillName, Integer level) {
        List<SkillResponse> skillRanks = this.getSkillRanks(skillName);
        Integer num = 0;

        for (SkillResponse skillResponse : skillRanks) {
            if (skillResponse.getSkill().getLevel() >= level) {
                num++;
            }
        }

        return skillRanks.subList(0, num);
    }

    // Validation Functions
    public boolean validateSkillName(String skillName) {
        return Arrays.stream(OSRS_SKILL.values()).anyMatch(s -> s.name().equals(skillName.toUpperCase()));
    }
}
