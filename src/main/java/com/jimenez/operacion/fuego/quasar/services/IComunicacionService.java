package com.jimenez.operacion.fuego.quasar.services;

import com.jimenez.operacion.fuego.quasar.models.dao.PositionDao;

public interface IComunicacionService
{
    public PositionDao getLocation(float... distances);
    public String getMessage(String[]... messages);
}
