package com.github.springboot.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder // Vai fazer a construção do objeto a partir da classe PAI
public class BadRequestExceptionDetails extends ExceptionDetails {
}
