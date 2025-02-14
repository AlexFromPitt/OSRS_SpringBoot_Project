package com.osrs_springboot_project.osrs_springboot_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.models.SkillResponse;
import com.osrs_springboot_project.osrs_springboot_project.services.SkillService;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping("/getSkillRanks/{skillName}")
    public ResponseEntity<List<SkillResponse>> getSkillRanks(@PathVariable String skillName) {
        return this.skillService.getSkillRanks(skillName);
    }
}
