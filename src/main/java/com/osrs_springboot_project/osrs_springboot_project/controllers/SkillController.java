package com.osrs_springboot_project.osrs_springboot_project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osrs_springboot_project.osrs_springboot_project.exceptions.SkillNotFoundException;
import com.osrs_springboot_project.osrs_springboot_project.models.SkillResponse;
import com.osrs_springboot_project.osrs_springboot_project.services.SkillService;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    SkillService skillService;

    @GetMapping("/getSkillRanks/{skillName}")
    public ResponseEntity<List<SkillResponse>> getSkillRanks(@PathVariable String skillName) {
        List<SkillResponse> skillsList = this.skillService.getSkillRanks(skillName);
        if (skillsList != null) {
            return ResponseEntity.ok(skillsList);
        } else {
            throw new SkillNotFoundException(skillName);
        }
    }

    @GetMapping("/getTopSkillRanks/{skillName}/{topNumber}")
    public ResponseEntity<List<SkillResponse>> getTopSkillRanks(
        @PathVariable String skillName,
        @PathVariable Integer topNumber) {
        List<SkillResponse> skillsList = this.skillService.getTopSkillRanks(skillName, topNumber);
        if (skillsList != null) {
            return ResponseEntity.ok(skillsList);
        } else {
            throw new SkillNotFoundException(skillName);
        }
    }
}
