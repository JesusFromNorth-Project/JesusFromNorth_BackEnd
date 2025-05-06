package org.example.clinic_system.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class SuccessMessage <T> {
    private HttpStatus status;
    private String message;
    private T data;

}
