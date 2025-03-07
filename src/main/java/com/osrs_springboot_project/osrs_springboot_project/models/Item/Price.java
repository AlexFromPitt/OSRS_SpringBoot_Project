package com.osrs_springboot_project.osrs_springboot_project.models.Item;

import lombok.Data;

@Data
public class Price {
    private String trend;
    private Integer price;

    public Price(String trend, Integer price) {
        this.trend = trend;
        this.price = price;
    }
}
