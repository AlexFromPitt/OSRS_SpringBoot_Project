package com.osrs_springboot_project.osrs_springboot_project.models;

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
}
