package com.github.springboot.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    // Campos adicionais para erros de validação
    private final String fields;
    private final String fieldsMessage;
}
