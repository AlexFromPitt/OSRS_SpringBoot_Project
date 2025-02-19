package com.osrs_springboot_project.osrs_springboot_project.models;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_ACTIVITIES;

import lombok.Data;

@Data
@Document(collection = "Activities")
public class Activities {
    @Id
    private String username;

    @Field("activities")
    private Map<OSRS_ACTIVITIES, Activity> activities;
}
