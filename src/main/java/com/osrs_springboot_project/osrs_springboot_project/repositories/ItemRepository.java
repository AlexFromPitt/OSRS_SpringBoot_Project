package com.osrs_springboot_project.osrs_springboot_project.repositories;

import com.osrs_springboot_project.osrs_springboot_project.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, Integer> {
}