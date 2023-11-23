package com.jimenez.operacion.fuego.quasar.services;

import com.jimenez.operacion.fuego.quasar.models.Satelite;
import com.jimenez.operacion.fuego.quasar.models.dao.FuenteResponseDao;
import com.jimenez.operacion.fuego.quasar.models.dao.PositionDao;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ComunicacionService implements IComunicacionService
{
    private List<Satelite> satelites;

    public ComunicacionService()
    {
        this.satelites = new ArrayList<Satelite>(List.of(
                new Satelite("kenobi", -500.0F, -200.0F),
                new Satelite("skywalker", 100.0F, -100.0F),
                new Satelite("sato", 500.0F, 100.0F)
        ));
    }

    public PositionDao getLocation(float... distances)
    {
        return new PositionDao(0.0F, 0.0F);
    }

    public String getMessage(String[]... messages)
    {
        return "message a descubrir";
    }

    public String descifrarMessage(List<String[]> messages)
    {
        StringBuilder messageDecrifado = new StringBuilder();

        int cantLength = messages.get(0).length;
        if (cantLength > messages.get(1).length) cantLength = messages.get(1).length;
        else if(cantLength > messages.get(2).length) cantLength = messages.get(2).length;

        String messageAnterior = messages.get(0)[0];
        messageDecrifado.append(messageAnterior + " ");
        for (int i = 0; i < cantLength; i++) {
            for (int j = 0; j < 3; j++) {
                if (messages.get(j)[i].equals(messageAnterior) || messages.get(j)[i].isBlank()) {
                    continue;
                }
                messageDecrifado.append(messages.get(j)[i] + " ");
                messageAnterior = messages.get(j)[i];
            }
        }

        return messageDecrifado.toString().trim();
    }

    public PositionDao descifrarPosition(List<Float> distancesList)
    {
        int longitud = distancesList.size();
        double[] arrayDouble = new double[longitud];

        for (int i = 0; i < longitud; i++) {
            arrayDouble[i] = distancesList.get(i);
        }

        double[][] positions = new double[][] {
                {this.satelites.get(0).getX(), this.satelites.get(0).getY()},
                {this.satelites.get(1).getX(), this.satelites.get(1).getY()},
                {this.satelites.get(2).getX(), this.satelites.get(2).getY()}
        };

        NonLinearLeastSquaresSolver trilateracion = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, arrayDouble), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = trilateracion.solve();
        double[] centroid = optimum.getPoint().toArray();

        return new PositionDao((float)centroid[0], (float)centroid[1]);
    }

    public FuenteResponseDao getSatelite(String name)
    {
        FuenteResponseDao fuenteResponseDao = null;

        Satelite satelite = this.satelites.stream()
                .filter(satelite1 -> name.equals(satelite1.getName()))
                .findAny()
                .orElse(null);

        if (satelite != null) {
            if(satelite.getMessage() == null) return fuenteResponseDao;
            fuenteResponseDao = new FuenteResponseDao(new PositionDao(satelite.getX(), satelite.getY()), Arrays.toString(satelite.getMessage()));
        }

        return fuenteResponseDao;
    }
}