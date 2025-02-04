package com.osrs_springboot_project.osrs_springboot_project.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "items")
public class Item {
    @Id
    private int id;
    private String icon;
    private String icon_large;
    private String type;
    private String typeIcon;
    private String name;
    private String description;
    private List<String> current;
    private List<String> today;
    private String trend;
    private String price;
    private boolean members;
}
