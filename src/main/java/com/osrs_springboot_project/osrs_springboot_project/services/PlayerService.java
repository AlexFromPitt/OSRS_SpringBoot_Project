package com.osrs_springboot_project.osrs_springboot_project.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.osrs_springboot_project.osrs_springboot_project.models.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill;

@Service
public class PlayerService {

    private static final String OSRS_PLAYER_INFO_URL = "https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=";

    public Player fetchPlayer(String username) {
        RestTemplate restTemplate = new RestTemplate();
        String url = OSRS_PLAYER_INFO_URL + username;
        String[] skillNames = {"Overall", "Attack", "Defense", "Strength", "Hitpoints", "Ranged", "Prayer", "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting", "Smithing", "Mining", "Herblore", "Agility", "Thieving", "Slayer", "Farming", "Runecrafting", "Hunter", "Construction"};
        Skill[] skillList = new Skill[24];
        try {
            String response = restTemplate.getForObject(url, String.class);
            String[] skills = response != null ? response.split("\n") : new String[0];

            for (int i = 0; i < 24 && i < skills.length; i++) {
                String[] skillData = skills[i].split(",");
                skillList[i] = buildSkill(skillNames[i], skillData);
            }

            return new Player(username, skillList);

        } catch (Exception e) {
            return null;
        }
    }

    public Skill buildSkill(String skillName, String[] skillData) {
        return Skill
            .builder()
            .name(skillName)
            .rank(Integer.parseInt(skillData[0]))
            .level(Short.parseShort(skillData[1]))
            .xp(Integer.parseInt(skillData[2]))
            .build();
    }
}
