package com.osrs_springboot_project.osrs_springboot_project.models;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Players")
public class Player {
    @Id
    private String username;
    private Skill overall;
    private Skill attack;
    private Skill defense;
    private Skill strength;
    private Skill hitpoints;
    private Skill ranged;
    private Skill prayer;
    private Skill magic;
    private Skill cooking;
    private Skill woodcutting;
    private Skill fletching;
    private Skill fishing;
    private Skill firemaking;
    private Skill crafting;
    private Skill smithing;
    private Skill mining;
    private Skill herblore;
    private Skill agility;
    private Skill thieving;
    private Skill slayer;
    private Skill farming;
    private Skill runecrafting;
    private Skill hunter;
    private Skill construction;

    public Player(String username, Skill[] skills) {
        this.username = username;
        this.overall = skills[0];
        this.attack = skills[1];
        this.defense = skills[2];
        this.strength = skills[3];
        this.hitpoints = skills[4];
        this.ranged = skills[5];
        this.prayer = skills[6];
        this.magic = skills[7];
        this.cooking = skills[8];
        this.woodcutting = skills[9];
        this.fletching = skills[10];
        this.fishing = skills[11];
        this.firemaking = skills[12];
        this.crafting = skills[13];
        this.smithing = skills[14];
        this.mining = skills[15];
        this.herblore = skills[16];
        this.agility = skills[17];
        this.thieving = skills[18];
        this.slayer = skills[19];
        this.farming = skills[20];
        this.runecrafting = skills[21];
        this.hunter = skills[22];
        this.construction = skills[23];
    }
}
