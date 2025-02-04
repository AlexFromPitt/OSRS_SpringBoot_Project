package com.osrs_springboot_project.osrs_springboot_project.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.models.Player;
import com.osrs_springboot_project.osrs_springboot_project.repositories.PlayerRepository;
import com.osrs_springboot_project.osrs_springboot_project.services.PlayerService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    PlayerService playerService;

    @PutMapping("fetchAndSavePlayerData/{username}")
    public ResponseEntity<String> putMethodName(@PathVariable String username) {
        Player player = playerService.fetchPlayer(username);

        if (player != null) {
            playerRepository.save(player);
            return ResponseEntity.ok("Player with username:" + username + " was found and saved to the database.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player with username:" + username + " was not found.");
        }
    }
}
