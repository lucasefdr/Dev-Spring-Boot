package com.github.springboot.handler;

import com.github.springboot.exception.BadRequestException;
import com.github.springboot.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // Aviso para todas as classes utilizarem o que está nessa classe baseados na flag @ExceptionHandler
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class) // Toda vez que houver um erro de Bad Request, usará esse padrão
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder() // "Construtor" de objeto
                        .timestamp(LocalDateTime.now()) // Horário do erro
                        .status(HttpStatus.BAD_REQUEST.value()) // Código HTTP
                        .title("Bad request exception, check the documentation") // Mensagem do erro customizada
                        .details(badRequestException.getMessage()) // Mensagem padrão do erro
                        .developerMessage(badRequestException.getClass().getName()) // Mensagem extra para devs
                        .build(), HttpStatus.BAD_REQUEST); // Constrói e retorna o o HTTP status
    }
}
