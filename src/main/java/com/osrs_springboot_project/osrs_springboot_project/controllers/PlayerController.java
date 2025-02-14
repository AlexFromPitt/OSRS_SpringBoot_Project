package com.osrs_springboot_project.osrs_springboot_project.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.models.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill;
import com.osrs_springboot_project.osrs_springboot_project.services.PlayerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
     * # Function Name: getPlayerData
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
        return this.playerService.getPlayerData(username);
    }

    /**
     * #################################################################################
     * # Function Name: getPlayerSkillData
     * # Description: 
     * #   GET Request function that is responsible for getting an Old School Runescape
     * #   player's data for a specific Skill.
     * #
     * # Parameters:
     * #   @param <username> - Username to be used when querying for the OSRS Data.
     * #   @param <skillName> - Name of the Skill to get the data for.
     * #
     * # Returns:
     * #   The Player's Skill Data associated to the passed in skillName.'
     * #
     * #################################################################################
     */
    @GetMapping("/getPlayerSkillData/{username}/{skillName}")
    public ResponseEntity<Skill> getPlayerSkillData(
        @PathVariable String username,
        @PathVariable String skillName) {
        return this.playerService.getPlayerSkillData(username, skillName);
    }

    /**
     * #################################################################################
     * # Function Name: getOverallSkillData
     * # Description: 
     * #   GET Request function that is responsible for getting an Old School Runescape
     * #   player's Overall Skill data.
     * #
     * # Parameters:
     * #   @param <username> - Username to be used when querying for the OSRS Data.
     * #
     * # Returns:
     * #   The Player's Overall Skill Data.
     * #
     * #################################################################################
     */
    @GetMapping("/getOverallPlayerSkillData/{username}")
    public ResponseEntity<Skill> getOverallPlayerSkillData(
        @PathVariable String username) {
        return this.playerService.getOverallPlayerSkillData(username);
    }

    /**
     * #################################################################################
     * # Function Name: getTopSkillData
     * # Description: 
     * #   GET Request function that is responsible for getting an Old School Runescape
     * #   player's Top Skills. Top Skills means Skills with the Highest Level/Experience
     * #   compared to the player's other Skills.
     * #
     * # Parameters:
     * #   @param <username> - Username to be used when querying for the OSRS Data.
     * #   @param <numTopSkills> - The number of Top Skills to return
     * #
     * # Returns:
     * #   The Player's Top <numTopSkills> in Descending order.
     * #
     * #################################################################################
     */
    @GetMapping("/getTopSkillData/{username}/{numTopSkills}")
    public ResponseEntity<List<Skill>> getAllPlayersSingleSkillData(
        @PathVariable String username,
        @PathVariable Integer numTopSkills) {
        return this.playerService.getPlayerTopSkills(username, numTopSkills);
    }

    /**
     * #################################################################################
     * # Function Name: getTopSkillData
     * # Description: 
     * #   DELETE Request function that is responsible for deleting the Player data
     * #   associated to the username from the database.
     * #
     * # Parameters:
     * #   @param <username> - Username to be used when querying for the OSRS Data.
     * #
     * # Returns:
     * #   A Success String if the Player was successfully deleted.
     * #
     * #################################################################################
     */
    @DeleteMapping("/deletePlayerData/{username}")
    public ResponseEntity<String> deletePlayerData(@PathVariable String username) {
        return this.playerService.deletePlayerData(username);
    }
}
