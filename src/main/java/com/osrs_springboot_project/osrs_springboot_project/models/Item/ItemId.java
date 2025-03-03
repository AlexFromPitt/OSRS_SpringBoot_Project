package com.osrs_springboot_project.osrs_springboot_project.models.Item;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ItemIds")
public class ItemId {
    @Id
    private String name;
    private Integer id;

    public ItemId(String name, Integer id) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return "Item: " + this.name + " ID: " + this.id;
    }
}
