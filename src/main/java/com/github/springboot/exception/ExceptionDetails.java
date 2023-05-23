package com.github.springboot.exception;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    private String title;
    private int status; // Status HTTP
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;
}
