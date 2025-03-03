package com.osrs_springboot_project.osrs_springboot_project.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String item) {
        super("Could not find Item Data for: " + item);
    }
}
