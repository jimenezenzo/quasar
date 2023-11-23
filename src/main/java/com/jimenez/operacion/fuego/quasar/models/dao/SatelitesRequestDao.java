package com.jimenez.operacion.fuego.quasar.models.dao;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SatelitesRequestDao(@NotEmpty(message = "The satelites is required.") @Size(min=3, max=3) @Valid List<SateliteRequestDao> satelites){}
