package com.osrs_springboot_project.osrs_springboot_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.services.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired ItemService itemService;

    @GetMapping("/getItemIds")
    public ResponseEntity<String> getItemIds() {
        if (this.itemService.getItemIDs()) {
            return ResponseEntity.ok("Successfully loaded all OSRS Item IDs.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was an issue loading the OSRS Item IDs.");
        }
    }
}
