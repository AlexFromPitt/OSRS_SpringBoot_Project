package com.osrs_springboot_project.osrs_springboot_project.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.osrs_springboot_project.osrs_springboot_project.models.Skill.Skills;

public interface SkillRepository extends MongoRepository<Skills, String> {

}
