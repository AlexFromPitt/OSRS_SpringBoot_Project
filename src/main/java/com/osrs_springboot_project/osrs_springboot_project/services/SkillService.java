package com.osrs_springboot_project.osrs_springboot_project.services;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;
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

        return skillRanks;
    }
}
