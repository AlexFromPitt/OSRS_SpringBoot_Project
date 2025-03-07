package com.osrs_springboot_project.osrs_springboot_project.models.Item;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Trend {
    @JsonProperty("trend")
    private String trend;

    @JsonProperty("change")
    private String change;
}
