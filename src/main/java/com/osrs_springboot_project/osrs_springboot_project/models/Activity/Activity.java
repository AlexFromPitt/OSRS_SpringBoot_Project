package com.osrs_springboot_project.osrs_springboot_project.models.Activity;

import java.util.Comparator;

import com.osrs_springboot_project.osrs_springboot_project.enums.OSRS_ACTIVITIES;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {
    private OSRS_ACTIVITIES name;
    private Integer rank;
    private Integer count;

    public String toString() {
        return "Activity name: " + this.name + " Rank: " + this.rank + " Count: " + this.count;
    }

    public static class ActivityComparator implements Comparator<Activity> {
        @Override
        public int compare(Activity a1, Activity a2) {
            int rank1 = a1.rank;
            int rank2 = a2.rank;
            int count1 = a1.count;
            int count2 = a2.count;
    
            if (rank1 == -1 && rank2 == -1) {
                return Integer.compare(count2, count1);
            }
    
            if (rank1 == -1) return 1;
            if (rank2 == -1) return -1;
    
            return Integer.compare(rank1, rank2);
        }
    }
}
