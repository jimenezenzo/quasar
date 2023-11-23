package com.jimenez.operacion.fuego.quasar.controllers;

import com.jimenez.operacion.fuego.quasar.exceptions.SateliteException;
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
        List<Float> distancesList = sateliteDao.satelites()
                .stream()
                .map(SateliteRequestDao::distance)
                .toList();

        List<String[]> messagesList = sateliteDao.satelites()
                .stream()
                .map(SateliteRequestDao::message)
                .toList();

        return new FuenteResponseDao(
                this.comunicacionService.descifrarPosition(distancesList),
                this.comunicacionService.descifrarMessage(messagesList)
        );
    }

    @PostMapping("topsecret_split/{name}")
    public FuenteResponseDao postSatelite(@Valid @RequestBody FuenteRequestDao fuenteRequestDao, @PathVariable String name)
    {
        throw new SateliteException();
    }

    @GetMapping("topsecret_split/{name}")
    public FuenteResponseDao getSatelite(@PathVariable String name)
    {
        FuenteResponseDao fuenteResponseDao = this.comunicacionService.getSatelite(name);

        if (fuenteResponseDao == null) {
            throw new SateliteException();
        }

        return fuenteResponseDao;
    }
}
