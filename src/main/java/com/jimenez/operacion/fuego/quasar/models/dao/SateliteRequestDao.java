package com.jimenez.operacion.fuego.quasar.models.dao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SateliteRequestDao(
        @NotBlank(message = "The name is required.") String name,
        @NotNull(message = "The distances is required.") float distance,
        @NotEmpty(message = "The message is required.") String[] message
){}
