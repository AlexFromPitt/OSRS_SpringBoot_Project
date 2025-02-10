package com.osrs_springboot_project.osrs_springboot_project.enums;

public enum OSRS_SKILL {

    OVERALL("Overall"),
    ATTACK("Attack"),
    DEFENSE("Defense"),
    STRENGTH("Strength"),
    HITPOINTS("Hitpoints"),
    RANGED("Ranged"),
    PRAYER("Prayer"),
    MAGIC("Magic"),
    COOKING("Cooking"),
    WOODCUTTING("Woodcutting"),
    FLETCHING("Fletching"),
    FISHING("Fishing"),
    FIREMAKING("Firemaking"),
    CRAFTING("Crafting"),
    SMITHING("Smithing"),
    MINING("Mining"),
    HERBLORE("Herblore"),
    AGILITY("Agility"),
    THIEVING("Thieving"),
    SLAYER("Slayer"),
    FARMING("Farming"),
    RUNECRAFTING("Runecrafting"),
    HUNTER("Hunter"),
    CONSTRUCTION("Construction");


    private final String skillName;

    OSRS_SKILL(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }
}
