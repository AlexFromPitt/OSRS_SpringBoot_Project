package com.osrs_springboot_project.osrs_springboot_project.models.Item;

import lombok.Data;

@Data
public class ItemPriceData {
    private int id;
    private Price currentPrice;
    private Price todayPrice;
    private Trend day30;
    private Trend day90;
    private Trend day180;

    @Data
    public static class Price {
        private String trend;
        private String price;
    }

    @Data
    public static class Trend {
        private String trend;
        private String change;
    }
}
