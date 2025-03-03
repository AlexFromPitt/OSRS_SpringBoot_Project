package com.osrs_springboot_project.osrs_springboot_project.models.Activity;

import java.util.Comparator;

import lombok.Data;

@Data
public class ActivityResponse {
    private String username;
    private Activity activity;

    public ActivityResponse(String username, Activity activity) {
        this.username = username;
        this.activity = activity;
    }

    public static class ActivityResponseComparator implements Comparator<ActivityResponse> {
        @Override
        public int compare(ActivityResponse a1, ActivityResponse a2) {
            int rank1 = a1.activity.getRank();
            int rank2 = a2.activity.getRank();
            int count1 = a1.activity.getCount();
            int count2 = a2.activity.getCount();
    
            if (rank1 == -1 && rank2 == -1) {
                return Integer.compare(count2, count1);
            }
    
            if (rank1 == -1) return 1;
            if (rank2 == -1) return -1;
    
            return Integer.compare(rank1, rank2);
        }
    }
}
