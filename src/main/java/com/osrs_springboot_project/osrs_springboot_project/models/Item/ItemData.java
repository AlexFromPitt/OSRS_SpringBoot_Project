package com.osrs_springboot_project.osrs_springboot_project.models.Item;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemData {
    @JsonProperty("icon")
    private String icon;

    @JsonProperty("icon_large")
    private String iconLarge;

    @Id
    @JsonProperty("id")
    private int id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("typeIcon")
    private String typeIcon;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("current")
    private Price currentPrice;

    @JsonProperty("today")
    private Price todayPrice;

    @JsonProperty("members")
    private String members;

    @JsonProperty("day30")
    private Trend day30;

    @JsonProperty("day90")
    private Trend day90;

    @JsonProperty("day180")
    private Trend day180;

    @Data
    public static class Price {
        @JsonProperty("trend")
        private String trend;

        @JsonProperty("price")
        private String price;
    }
}
