package com.osrs_springboot_project.osrs_springboot_project.services;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;
import com.osrs_springboot_project.osrs_springboot_project.models.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.SkillResponse;
import com.osrs_springboot_project.osrs_springboot_project.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    PlayerRepository playerRepository;

    public ResponseEntity<List<SkillResponse>> getSkillRanks(String skillName) {
        List<Player> playerList = this.playerRepository.findAll();
        List<SkillResponse> skillRanks = new ArrayList<>();

        for (Player player : playerList) {
            skillRanks.add(new SkillResponse(
                player.getUsername(), 
                player.getSkill(OSRS_SKILL.valueOf(skillName.toUpperCase()))));
        }

        return ResponseEntity.ok(skillRanks);
    }
}
