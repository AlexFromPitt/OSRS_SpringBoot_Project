package com.osrs_springboot_project.osrs_springboot_project.services;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;
import com.osrs_springboot_project.osrs_springboot_project.models.ActivityResponse;
import com.osrs_springboot_project.osrs_springboot_project.models.SkillResponse;
import com.osrs_springboot_project.osrs_springboot_project.models.Skills;
import com.osrs_springboot_project.osrs_springboot_project.repositories.SkillRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

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
}
