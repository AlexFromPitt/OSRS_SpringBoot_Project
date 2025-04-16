package com.osrs_springboot_project.osrs_springboot_project.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.osrs_springboot_project.osrs_springboot_project.models.Activity.Activity;

@Repository
public class ActivityRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Activity> findByUsername(String username) {
        String sql = "SELECT * FROM Activities WHERE username = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Activity.class), username);
    }
}
