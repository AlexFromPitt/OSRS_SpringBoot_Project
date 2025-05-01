package com.osrs_springboot_project.osrs_springboot_project.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.osrs_springboot_project.osrs_springboot_project.models.Item.ItemId;

public interface ItemRepository extends MongoRepository<ItemId, String> {

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<ItemId> findByNameContainingIgnoreCase(String itemName);
}
