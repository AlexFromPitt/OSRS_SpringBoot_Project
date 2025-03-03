package com.osrs_springboot_project.osrs_springboot_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.exceptions.ActivityNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.models.Activity.ActivityResponse;
import com.osrs_springboot_project.osrs_springboot_project.services.ActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @GetMapping("/getActivityRanks/{activityName}")
    public ResponseEntity<List<ActivityResponse>> getActivityRanks(@PathVariable String activityName) {
        List<ActivityResponse> activitiesList = this.activityService.getActivityRanks(activityName);
        if (activitiesList != null) {
            return ResponseEntity.ok(activitiesList);
        } else {
            throw new ActivityNotFoundException(activityName);
        }
    }

    @GetMapping("/getTopActivityRanks/{activityName}/{topNumber}")
    public ResponseEntity<List<ActivityResponse>> getTopActivityRanks(
        @PathVariable String activityName,
        @PathVariable Integer topNumber) {
        List<ActivityResponse> activitiesList = this.activityService.getTopActivityRanks(activityName, topNumber);
        if (activitiesList != null) {
            return ResponseEntity.ok(activitiesList);
        } else {
            throw new ActivityNotFoundException(activityName);
        }
    }

    @GetMapping("/getActivityRanksGreaterThanCount/{activityName}/{count}")
    public ResponseEntity<List<ActivityResponse>> getActivityRanksGreaterThanCount(
        @PathVariable String activityName,
        @PathVariable Integer count) {
        List<ActivityResponse> activitiesList = this.activityService.getActivityRanksGreaterThanCount(activityName, count);
        if (activitiesList != null) {
            return ResponseEntity.ok(activitiesList);
        } else {
            throw new ActivityNotFoundException(activityName);
        }
    }
}
