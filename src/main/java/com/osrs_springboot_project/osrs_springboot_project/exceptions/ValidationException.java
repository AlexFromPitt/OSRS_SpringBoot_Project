package com.osrs_springboot_project.osrs_springboot_project.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String errortext) {
        super(errortext);
    }
}