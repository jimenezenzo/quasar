package com.jimenez.operacion.fuego.quasar.controllers;

import com.jimenez.operacion.fuego.quasar.models.dao.*;
import com.jimenez.operacion.fuego.quasar.services.IComunicacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ComunicacionRestController
{
    @Autowired
    private IComunicacionService comunicacionService;

    @PostMapping("topsecret")
    public FuenteResponseDao postSatelites(@Valid @RequestBody SatelitesRequestDao sateliteDao)
    {
        float[] distances = new float[sateliteDao.satelites().size()];
        List<Float> distancesList = sateliteDao.satelites()
                .stream()
                .map(SateliteRequestDao::distance)
                .toList();

        int index = 0;
        for (final Float value: distancesList) {
            distances[index++] = value;
        }

        return new FuenteResponseDao(this.comunicacionService.getLocation(distances), "message");
    }

    @PostMapping("topsecret_split/{name}")
    public FuenteResponseDao postSatelite(@Valid @RequestBody FuenteRequestDao fuenteRequestDao, @PathVariable String name)
    {
        return new FuenteResponseDao(this.comunicacionService.getLocation(), "message");
    }

    @GetMapping("topsecret_split/{name}")
    public FuenteResponseDao getSatelite(@PathVariable String name)
    {
        return new FuenteResponseDao(this.comunicacionService.getLocation(), "message");
    }
}
