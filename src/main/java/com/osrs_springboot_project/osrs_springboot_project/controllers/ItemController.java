package com.osrs_springboot_project.osrs_springboot_project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.osrs_springboot_project.osrs_springboot_project.models.Item;
import com.osrs_springboot_project.osrs_springboot_project.repositories.ItemRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @PutMapping
    public ResponseEntity<Item> createOrUpdateItem(@RequestBody Item item) {
        Item savedItem = itemRepository.save(item);
        return ResponseEntity.ok(savedItem);
    }
}
