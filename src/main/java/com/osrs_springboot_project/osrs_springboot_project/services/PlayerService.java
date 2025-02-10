package com.osrs_springboot_project.osrs_springboot_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.PlayerNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.SkillNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.models.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill;
import com.osrs_springboot_project.osrs_springboot_project.models.SkillResponse;
import com.osrs_springboot_project.osrs_springboot_project.repositories.PlayerRepository;

@Service
public class PlayerService {

    private static final String OSRS_PLAYER_INFO_URL = "https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=";
    private static final int NUM_SKILLS = 24;

    @Autowired
    PlayerRepository playerRepository;

    public ResponseEntity<String> fetchAndSavePlayerData(String username) {
        Player player = null;
        RestTemplate restTemplate = new RestTemplate();
        String url = OSRS_PLAYER_INFO_URL + username;
        OSRS_SKILL[] skillNames = OSRS_SKILL.values();
        Skill[] skillList = new Skill[NUM_SKILLS];

        if (!this.playerRepository.existsById(username)) {
            try {
                String response = restTemplate.getForObject(url, String.class);
                String[] skills = response != null ? response.split("\n") : new String[0];

                for (int i = 0; i < NUM_SKILLS && i < skills.length; i++) {
                    String[] skillData = skills[i].split(",");
                    skillList[i] = buildSkill(skillNames[i], skillData);
                }
                player = new Player(username, skillList);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player with username:" + username + " was not found.");
            }

            this.playerRepository.save(player);
            return ResponseEntity.ok("Player with username:" + username + " was found and saved to the database.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player with username:" + username + " is already saved in the database.");
        }
    }

    public ResponseEntity<Player> getPlayerData(String username) {
        Player player = this.playerRepository.findById(username)
            .orElseThrow(() -> new PlayerNotFoundException(username));
        return ResponseEntity.ok(player);
    }

    public ResponseEntity<String> deletePlayerData(String username) {
        if (this.playerRepository.existsById(username)) {
            this.playerRepository.deleteById(username);
            return ResponseEntity.ok("Player with username: " + username + " was successfully removed from the database.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player with username: " + username + " was not found in the database.");
        }
    }

    public ResponseEntity<SkillResponse> getPlayerSkillData(String username, OSRS_SKILL skillName) {
        Player player = this.playerRepository.findById(username)
            .orElseThrow(() -> new PlayerNotFoundException(username));
        
        if (player.getSkill(skillName) != null) {
            return ResponseEntity.ok(new SkillResponse(username, player.getSkill(skillName)));
        } else {
            throw new SkillNotFoundException(username, skillName);
        }
    }

    public Skill buildSkill(OSRS_SKILL skillName, String[] skillData) {
        return Skill
            .builder()
            .skillName(skillName)
            .rank(Integer.parseInt(skillData[0]))
            .level(Short.parseShort(skillData[1]))
            .xp(Integer.parseInt(skillData[2]))
            .build();
    }
}
