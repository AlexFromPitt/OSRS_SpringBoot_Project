package com.osrs_springboot_project.osrs_springboot_project.services;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.PlayerNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.SkillNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.ValidationException;
import com.osrs_springboot_project.osrs_springboot_project.models.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill;
import com.osrs_springboot_project.osrs_springboot_project.repositories.PlayerRepository;

@Service
public class PlayerService {

    private static final String OSRS_PLAYER_INFO_URL = "https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=";
    private static final int NUM_SKILLS = 24;

    @Autowired
    PlayerRepository playerRepository;

    /* Player Services */
    public ResponseEntity<Player> getPlayerData(String username) {
        return ResponseEntity.ok(fetchPlayerData(username));
    }

    public ResponseEntity<String> deletePlayerData(String username) {
        this.validateUsername(username);

        if (this.playerRepository.existsById(username)) {
            this.playerRepository.deleteById(username);
            return ResponseEntity.ok("Player with username: " + username + " was successfully removed from the database.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player with username: " + username + " was not found in the database.");
        }
    }

    public ResponseEntity<Skill> getPlayerSkillData(String username, String skillName) {
        Player player = this.playerRepository.findById(username)
            .orElseThrow(() -> new PlayerNotFoundException(username));
        Skill skillData;

        this.validateUsername(username);
        
        try {
            skillData = player.getSkill(OSRS_SKILL.valueOf(skillName.toUpperCase()));
            return ResponseEntity.ok(skillData);
        } catch (IllegalArgumentException e) {
            throw new SkillNotFoundException(username, skillName);
        }
    }

    public ResponseEntity<Skill> getOverallPlayerSkillData(String username) {
        return ResponseEntity.ok(fetchPlayerData(username).getOverall());
    }

    public ResponseEntity<List<Skill>> getPlayerTopSkills(String username, Integer numTopSkills) {
        Player player = this.fetchPlayerData(username);
        Collection<Skill> allSkillValues = player.getSkills().values();
        List<Skill> allSkillsList = new ArrayList<>(allSkillValues);

        allSkillsList.sort(new Skill.SkillComparator().reversed());

        return ResponseEntity.ok(allSkillsList.subList(0, numTopSkills)); // start from 1 to skip Overall
    }

    /* Validation Functions */
    private void validateUsername(String username) {
        if (username.isEmpty()) {
            throw new ValidationException("Username cannot be empty.");
        }
    }

    /* Helper Functions */
    private Skill buildSkill(OSRS_SKILL skillName, String[] skillData) {
        return Skill
            .builder()
            .skillName(skillName)
            .rank(Integer.parseInt(skillData[0]))
            .level(Short.parseShort(skillData[1]))
            .xp(Integer.parseInt(skillData[2]))
            .build();
    }

    public Player fetchPlayerData(String username) {
        Player player;

        this.validateUsername(username);

        if (this.playerRepository.existsById(username)) {
            player = this.playerRepository.findById(username)
                    .orElseThrow(() -> new PlayerNotFoundException(username));
            
            if (this.isDataOutdated(player.getLastUpdate())) return fetchPlayerDataFromServer(username);
            else return player;
        } else {
            return fetchPlayerDataFromServer(username);
        }
    }

    public Player fetchPlayerDataFromServer(String username) {
        Player player = null;
        RestTemplate restTemplate = new RestTemplate();
        String url = OSRS_PLAYER_INFO_URL + username;
        OSRS_SKILL[] skillNames = OSRS_SKILL.values();
        Skill[] skillList = new Skill[NUM_SKILLS];

        try {
            String response = restTemplate.getForObject(url, String.class);
            String[] skills = response != null ? response.split("\n") : new String[0];

            for (int i = 0; i < NUM_SKILLS && i < skills.length; i++) {
                String[] skillData = skills[i].split(",");
                skillList[i] = buildSkill(skillNames[i], skillData);
            }
            player = new Player(username, skillList);

        } catch (Exception e) {
            return player;
        }

        this.playerRepository.save(player);
        return player;
    }

    private boolean isDataOutdated(Instant lastUpdate) {
        if (lastUpdate == null) {
            return true;
        }
        // Check if more than 1 day has passed since `lastUpdate`
        Instant now = Instant.now();
        Duration duration = Duration.between(lastUpdate, now);
        return duration.toDays() > 1;
    }
}
