package com.osrs_springboot_project.osrs_springboot_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class PlayerUpdateService {

    @Autowired
    private PlayerService playerService;

    @PostConstruct
    public void getPlayerDataOnStartup() {
        playerService.updatePlayerDbData();
        return;
    }

    @Scheduled(fixedRate = 3600000)
    public void getPlayerDataHourly() {
        playerService.updatePlayerDbData();
        return;
    }
}
