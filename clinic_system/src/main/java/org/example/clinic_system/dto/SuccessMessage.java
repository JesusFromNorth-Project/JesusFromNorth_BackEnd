package org.example.clinic_system.dto;

import org.springframework.http.HttpStatus;

public class SuccessMessage <T> {
    private HttpStatus status;
    private String message;
    private T data;
}
