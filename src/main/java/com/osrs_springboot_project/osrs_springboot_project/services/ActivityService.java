package com.osrs_springboot_project.osrs_springboot_project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_ACTIVITIES;
import com.osrs_springboot_project.osrs_springboot_project.models.Activity.Activities;
import com.osrs_springboot_project.osrs_springboot_project.models.Activity.ActivityResponse;
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

    public List<ActivityResponse> getActivityRanksGreaterThanCount(String activityName, Integer count) {
        List<ActivityResponse> activityRanks = this.getActivityRanks(activityName);
        Integer num = 0;

        for (ActivityResponse activityResponse : activityRanks) {
            if (activityResponse.getActivity().getCount() >= count) {
                num++;
            }
        }

        return activityRanks.subList(0, num);
    }
}
