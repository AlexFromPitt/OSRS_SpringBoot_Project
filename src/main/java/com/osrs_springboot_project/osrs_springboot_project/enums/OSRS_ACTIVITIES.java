package com.osrs_springboot_project.osrs_springboot_project.enums;

public enum OSRS_ACTIVITIES {
    LEAGUE_POINTS("League_Points"),
    DEADMAN_POINTS("Deadman_Points"),
    BOUNTY_HUNTER_HUNTER("Bounty_Hunter_Hunter"),
    BOUNTY_HUNTER_ROGUE("Bounty_Hunter_Rogue"),
    BOUNTY_HUNTER_HUNTER_LEGACY("Bounty_Hunter_Hunter_Legacy"),
    BOUNTY_HUNTER_ROGUE_LEGACY("Bounty_Hunter_Rogue_Legacy"),
    CLUE_SCROLLS("Clue_Scrolls"),
    EASY_CLUES("Easy_Clues"),
    MEDIUM_CLUES("Medium_Clues"),
    HARD_CLUES("Hard_Clues"),
    ELITE_CLUES("Elite_Clues"),
    MASTER_CLUES("Master_Clues");

    private String activityName;

    OSRS_ACTIVITIES(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityname() {
        return this.activityName;
    }
}
