package com.osrs_springboot_project.osrs_springboot_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OsrsSpringbootProjectApplication {

	public static void main(String[] args) {
        System.out.println("Starting Spring Boot Application...");
		SpringApplication.run(OsrsSpringbootProjectApplication.class, args);
        System.out.println("Application started successfully.");
	}

}