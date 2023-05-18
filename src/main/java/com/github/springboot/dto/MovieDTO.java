package com.github.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MovieDTO {
    public Long id;

//    @NotEmpty(message = "Name cannot be empty")
//    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be empty, null or blank")
    public String name;
}
