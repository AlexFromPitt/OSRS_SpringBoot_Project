package com.osrs_springboot_project.osrs_springboot_project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.osrs_springboot_project.osrs_springboot_project.exceptions.PlayerNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.models.Activity.Activities;
import com.osrs_springboot_project.osrs_springboot_project.models.Player.Player;
import com.osrs_springboot_project.osrs_springboot_project.models.Player.Username;
import com.osrs_springboot_project.osrs_springboot_project.models.Skill.Skills;

@Repository
public class PlayerRepository {

    @Autowired
    UsernameRepository usernameRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Transactional
    public void savePlayer(Player player) {
        this.usernameRepository.save(new Username(player.getUsername()));
        this.skillRepository.save(new Skills(player.getUsername(), player.getOverall(), player.getSkills()));
        this.activityRepository.save(new Activities(player.getUsername(), player.getActivities()));
    }

    @Transactional
    public void deletePlayer(String username) {
        this.usernameRepository.deleteById(username);
        this.skillRepository.deleteById(username);
        this.activityRepository.deleteById(username);
    }

    public Skills getPlayerSkills(String username) {
        return this.skillRepository.findById(username)
            .orElseThrow(() -> new PlayerNotFoundException(username));
    }

    public Activities getPlayerActivities(String username) {
        return this.activityRepository.findById(username)
            .orElseThrow(() -> new PlayerNotFoundException(username));
    }

    public Username getUsernameMetadata(String username) {
        return this.usernameRepository.findById(username)
            .orElseThrow(() -> new PlayerNotFoundException(username));
    }

    public Boolean doesPlayerExist(String username) {
        return this.usernameRepository.existsById(username);
    } 
}
