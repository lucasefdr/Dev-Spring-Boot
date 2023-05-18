package com.github.springboot.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {
    private String title;
    private int status; // Status HTTP
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;
}
