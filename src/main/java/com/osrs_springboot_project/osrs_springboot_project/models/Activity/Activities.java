package com.osrs_springboot_project.osrs_springboot_project.models.Activity;

import java.util.Map;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_ACTIVITIES;

import lombok.Data;

@Data
public class Activities {
    private String username;
    private Map<OSRS_ACTIVITIES, Activity> activities;

    public Activities() {}

    public Activities(String username, Map<OSRS_ACTIVITIES, Activity> activities) {
        this.username = username;
        this.activities = activities;
    }

    public Activity getActivity(OSRS_ACTIVITIES activityName){
        return this.activities.get(activityName);
    }
}
