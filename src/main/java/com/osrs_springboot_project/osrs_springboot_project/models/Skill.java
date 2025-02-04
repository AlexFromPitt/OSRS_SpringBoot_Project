package com.osrs_springboot_project.osrs_springboot_project.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    private String name;
    private Integer rank;
    private Short level;
    private Integer xp;
}
