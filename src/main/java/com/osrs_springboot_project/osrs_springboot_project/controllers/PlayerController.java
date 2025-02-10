package com.osrs_springboot_project.osrs_springboot_project.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.models.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill;
import com.osrs_springboot_project.osrs_springboot_project.models.SkillResponse;
import com.osrs_springboot_project.osrs_springboot_project.services.PlayerService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PutMapping("/fetchAndSavePlayerData/{username}")
    public ResponseEntity<String> fetchAndSavePlayerData(@PathVariable String username) {
        return playerService.fetchAndSavePlayerData(username);
    }

    @GetMapping("/getPlayerData/{username}")
    public ResponseEntity<Player> getPlayerData(@PathVariable String username) {
        return playerService.getPlayerData(username);
    }

    @GetMapping("/getPlayerSkillData/{username}/{skillName}")
    public ResponseEntity<SkillResponse> getPlayerSkillData(
        @PathVariable String username,
        @PathVariable String skillName) {
        return playerService.getPlayerSkillData(username, skillName);
    }

    @DeleteMapping("/deletePlayerData/{username}")
    public ResponseEntity<String> deletePlayerData(@PathVariable String username) {
        return playerService.deletePlayerData(username);
    }
}
