package com.hms.doctor_service.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponseDTO {
    private final LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;

    public ErrorResponseDTO( int status, String error, String message) {
        this.timeStamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }
}

