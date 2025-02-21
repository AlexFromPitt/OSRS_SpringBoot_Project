package com.osrs_springboot_project.osrs_springboot_project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_ACTIVITIES;
import com.osrs_springboot_project.osrs_springboot_project.models.Activities;
import com.osrs_springboot_project.osrs_springboot_project.models.ActivityResponse;
import com.osrs_springboot_project.osrs_springboot_project.repositories.ActivityRepository;

@Service
public class ActivityService {
    
    @Autowired
    ActivityRepository activityRepository;

    public List<ActivityResponse> getActivityRanks(String activityName) {
        List<Activities> activitiesList = this.activityRepository.findAll();
        List<ActivityResponse> activityRanks = new ArrayList<>();

        for (Activities activities : activitiesList) {
            activityRanks.add(new ActivityResponse(
                activities.getUsername(), 
                activities.getActivity(OSRS_ACTIVITIES.valueOf(activityName.toUpperCase()))));
        }
        activityRanks.sort(new ActivityResponse.ActivityResponseComparator());
        
        return activityRanks;
    }

    public List<ActivityResponse> getTopActivityRanks(String activityName, Integer topNumber) {
        List<ActivityResponse> activityRanks = this.getActivityRanks(activityName);

        return activityRanks.subList(0, topNumber);
    }
}
