package com.osrs_springboot_project.osrs_springboot_project.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.PlayerNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.SkillNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.models.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill;
import com.osrs_springboot_project.osrs_springboot_project.services.PlayerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Player player = this.playerService.getPlayerData(username);
        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            throw new PlayerNotFoundException(username);
        }
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
        Skill skill = this.playerService.getPlayerSkillData(username, skillName);
        if (skill != null) {
            return ResponseEntity.ok(skill);
        } else {
            throw new SkillNotFoundException(username, skillName);
        }
    }

    /**
     * #################################################################################
     * # Function Name: getOverallPlayerSkillData
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
        Skill overall = this.playerService.getOverallPlayerSkillData(username);
        if (overall != null) {
            return ResponseEntity.ok(overall);
        } else{
            throw new SkillNotFoundException(username, OSRS_SKILL.OVERALL.toString());
        }
    }

    /**
     * #################################################################################
     * # Function Name: getPlayerTopSkillsData
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
    @GetMapping("/getPlayerTopSkillsData/{username}/{numTopSkills}")
    public ResponseEntity<List<Skill>> getPlayerTopSkillsData(
        @PathVariable String username,
        @PathVariable Integer numTopSkills) {
        List<Skill> skillList = this.playerService.getPlayerTopSkills(username, numTopSkills);
        if (skillList != null) {
            return ResponseEntity.ok(skillList);
        } else {
            throw new PlayerNotFoundException(username);
        }
    }

    /**
     * #################################################################################
     * # Function Name: deletePlayerData
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
        if (this.playerService.deletePlayerData(username)) return ResponseEntity.ok("Player with username: " + username + " was successfully removed from the database.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player with username: " + username + " was not found in the database.");
    }
}
