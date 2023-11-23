package com.jimenez.operacion.fuego.quasar.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Satelite
{
    @NotBlank(message = "The name is required.")
    public String name;
    @NotNull(message = "The x is required.")
    public float x;
    @NotNull(message = "The y is required.")
    public float y;

    public Satelite(String name, float x, float y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
