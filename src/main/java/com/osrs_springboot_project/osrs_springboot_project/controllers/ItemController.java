package com.osrs_springboot_project.osrs_springboot_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.exceptions.ItemNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.models.Item.ItemInfoData;
import com.osrs_springboot_project.osrs_springboot_project.models.Item.ItemPriceData;
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

    @GetMapping("/getItemInfoData/{itemName}")
    public ResponseEntity<ItemInfoData> getItemInfoData(@PathVariable String itemName) {
        ItemInfoData itemInfo = this.itemService.getItemInfoData(itemName);
        if (itemInfo != null) {
            return ResponseEntity.ok(itemInfo);
        } else {
            throw new ItemNotFoundException(itemName);
        }
    }

    @GetMapping("/getItemPriceData/{itemName}")
    public ResponseEntity<ItemPriceData> getItemPriceData(@PathVariable String itemName) {
        ItemPriceData itemInfo = this.itemService.getItemPriceData(itemName);
        if (itemInfo != null) {
            return ResponseEntity.ok(itemInfo);
        } else {
            throw new ItemNotFoundException(itemName);
        }
    }
}
