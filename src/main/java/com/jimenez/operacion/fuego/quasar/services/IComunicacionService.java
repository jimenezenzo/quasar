package com.jimenez.operacion.fuego.quasar.services;

import com.jimenez.operacion.fuego.quasar.models.Satelite;
import com.jimenez.operacion.fuego.quasar.models.dao.FuenteResponseDao;
import com.jimenez.operacion.fuego.quasar.models.dao.PositionDao;

import java.util.List;

public interface IComunicacionService
{
    PositionDao getLocation(float... distances);
    String getMessage(String[]... messages);
    String descifrarMessage(List<String[]> messages);
    PositionDao descifrarPosition(List<Float> distancesList);
    FuenteResponseDao getSatelite(String name);
}
