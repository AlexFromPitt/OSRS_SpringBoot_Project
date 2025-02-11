package com.osrs_springboot_project.osrs_springboot_project.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.models.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.SkillResponse;
import com.osrs_springboot_project.osrs_springboot_project.services.PlayerService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;


    /**
     * #################################################################################
     * # Function Name: fetchAndSavePlayerData
     * # Description: 
     * #   GET Request function that is responsible for getting the Old School Runescape
     * #   player data from the public API or Database if already available.
     * #
     * # Parameters:
     * #   @param <username> - Username to be used when querying for the OSRS Data.
     * #
     * # Returns:
     * #   The Player data associated to the requested username.
     * #
     * #################################################################################
     */
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
