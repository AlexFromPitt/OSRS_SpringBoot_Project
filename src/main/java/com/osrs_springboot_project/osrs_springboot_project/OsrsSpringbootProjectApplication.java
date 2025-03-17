package com.osrs_springboot_project.osrs_springboot_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class OsrsSpringbootProjectApplication {

	public static void main(String[] args) {
		// Load environment variables from the .env file
        Dotenv dotenv = Dotenv.load();
        
        // Optionally, print the value to verify it's loaded
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
        
        System.out.println("Starting Spring Boot Application...");
		SpringApplication.run(OsrsSpringbootProjectApplication.class, args);
	}
}