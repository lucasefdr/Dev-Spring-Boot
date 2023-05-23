package com.github.springboot.handler;

import com.github.springboot.exception.BadRequestException;
import com.github.springboot.exception.BadRequestExceptionDetails;
import com.github.springboot.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice // Aviso para todas as classes utilizarem o que está nessa classe baseados na flag @ExceptionHandler
@Log4j2
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class) // Toda vez que houver um erro de Bad Request, usará esse padrão
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder() // "Construtor" de objeto
                        .timestamp(LocalDateTime.now()) // Horário do erro
                        .status(HttpStatus.BAD_REQUEST.value()) // Código HTTP
                        .title("Bad request exception, check the documentation") // Mensagem do erro customizada
                        .details(ex.getMessage()) // Mensagem padrão do erro
                        .developerMessage(ex.getClass().getName()) // Mensagem extra para devs
                        .build(), HttpStatus.BAD_REQUEST); // Constrói e retorna o HTTP status
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerValidationExceptionDetails(MethodArgumentNotValidException ex) {
//        log.info("Fields {}", ex.getBindingResult().getFieldError().getField());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(x -> x.getField()).collect(Collectors.joining(", "));
        String messages = fieldErrors.stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now()) // Horário do erro
                        .status(HttpStatus.BAD_REQUEST.value()) // Código HTTP
                        .title("Bad request exception, check the documentation") // Mensagem do erro customizada
                        .details("Check the field(s) error(s)") // Mensagem padrão do erro
                        .developerMessage(ex.getClass().getName()) // Mensagem extra para devs
                        .fields(fields) // Captura o campo do erro
                        .fieldsMessage(messages) // Captura a mensagem do erro no campo
                        .build(), HttpStatus.BAD_REQUEST); // Constrói e retorna o HTTP status
    }
}
