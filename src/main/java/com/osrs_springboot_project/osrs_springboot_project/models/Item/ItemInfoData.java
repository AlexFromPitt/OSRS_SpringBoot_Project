package com.osrs_springboot_project.osrs_springboot_project.models.Item;

import lombok.Data;

@Data
public class ItemInfoData {
    private int id;
    private String icon;
    private String iconLarge;
    private String type;
    private String typeIcon;
    private String name;
    private String description;
    private String members;
}
