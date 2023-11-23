package com.jimenez.operacion.fuego.quasar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No hay suficiente información")
public class SateliteException extends RuntimeException {}