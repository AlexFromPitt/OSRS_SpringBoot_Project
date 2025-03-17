package com.osrs_springboot_project.osrs_springboot_project.services;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_ACTIVITIES;
import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_SKILL;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.SkillNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.exceptions.ValidationException;
import com.osrs_springboot_project.osrs_springboot_project.models.Activity.Activities;
import com.osrs_springboot_project.osrs_springboot_project.models.Activity.Activity;
import com.osrs_springboot_project.osrs_springboot_project.models.Player.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.Player.Username;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill.Skill;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill.Skills;
import com.osrs_springboot_project.osrs_springboot_project.repositories.PlayerRepository;

@Service
public class PlayerService {

    private static final String OSRS_PLAYER_INFO_URL = "https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=";
    private static final int NUM_SKILLS = 24;

    @Autowired
    PlayerRepository playerRepository;

    /* Player Services */
    public Player getPlayerData(String username) {
        return fetchPlayerData(username);
    }

    public Boolean deletePlayerData(String username) {
        this.validateUsername(username);

        if (this.playerRepository.doesPlayerExist(username)) {
            this.playerRepository.deletePlayer(username);
            return true;
        } else return false;
    }

    public Skill getPlayerSkillData(String username, String skillName) {
        this.validateUsername(username);

        Skills skills = this.playerRepository.getPlayerSkills(username);
        Skill skillData;
        
        try {
            skillData = skills.getSkill(OSRS_SKILL.valueOf(skillName.toUpperCase()));
            return skillData;
        } catch (IllegalArgumentException e) {
            throw new SkillNotFoundException(username, skillName);
        }
    }

    public Skill getOverallPlayerSkillData(String username) {
        return fetchPlayerData(username).getOverall();
    }

    public List<Skill> getPlayerTopSkills(String username, Integer numTopSkills) {
        Player player = this.getPlayerData(username);
        Collection<Skill> allSkillValues = player.getSkills().values();
        List<Skill> allSkillsList = new ArrayList<>(allSkillValues);

        allSkillsList.sort(new Skill.SkillComparator());

        return allSkillsList.subList(0, numTopSkills);
    }

    @Async
    public void updatePlayerDbData() {
        List<Username> usernames = playerRepository.getAllUsernames();

        for (Username user : usernames) {
            fetchPlayerData(user.getUsername());
        }
        return;
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

    public Activity buildActivity(OSRS_ACTIVITIES activityName, String[] activityData) {
        return Activity
        .builder()
        .name(activityName)
        .rank(Integer.parseInt(activityData[0]))
        .count(Integer.parseInt(activityData[1]))
        .build();

    }

    public Player fetchPlayerData(String username) {
        this.validateUsername(username);

        if (this.playerRepository.doesPlayerExist(username)) {
            Username usernameData = this.playerRepository.getUsernameMetadata(username);
            
            if (this.isDataOutdated(usernameData.getLastUpdate())) return fetchPlayerDataFromServer(username);
            else return this.buildPlayerFromDB(username);
        } else {
            return fetchPlayerDataFromServer(username);
        }
    }

    public Player fetchPlayerDataFromServer(String username) {
        Player player = null;
        RestTemplate restTemplate = new RestTemplate();
        String url = OSRS_PLAYER_INFO_URL + username;
        OSRS_SKILL[] skillNames = OSRS_SKILL.values();
        OSRS_ACTIVITIES[] activityNames = OSRS_ACTIVITIES.values();
        Skill[] skillList = new Skill[NUM_SKILLS];
        Activity[] activityList = new Activity[activityNames.length];
        int idx = 0; // Will be used to maintain index of playerData.

        try {
            String response = restTemplate.getForObject(url, String.class);
            String[] playerData = response != null ? response.split("\n") : new String[0];

            // Loop through the Skill Data first.
            for (int i = 0; i < skillNames.length; i++) {
                String[] skillData = playerData[idx].split(",");
                skillList[i] = buildSkill(skillNames[i], skillData);
                idx++;
            }

            // Loop through the Activities
            for (int i=0; i < activityNames.length; i++) {
                String[] activityData = playerData[idx++].split(",");
                activityList[i] = buildActivity(activityNames[i], activityData);
            }

            // Create entire Player object to return to the Front End.
            player = new Player(username, skillList, activityList);

            this.playerRepository.savePlayer(player);

        } catch (Exception e) {
            return player;
        }

        return player;
    }

    private Player buildPlayerFromDB(String username) {
        Skills skills = this.playerRepository.getPlayerSkills(username);
        Activities activities = this.playerRepository.getPlayerActivities(username);
        
        return new Player(username, skills.getOverall(), skills.getSkills(), activities.getActivities());
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
