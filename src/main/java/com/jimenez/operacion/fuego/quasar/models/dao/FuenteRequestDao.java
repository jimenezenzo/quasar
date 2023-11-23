package com.jimenez.operacion.fuego.quasar.models.dao;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FuenteRequestDao(@NotNull(message = "The distances is required.") float distance, @NotEmpty(message = "The message is required.") String[] messages){}
