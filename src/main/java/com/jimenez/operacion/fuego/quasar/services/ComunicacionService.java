package com.jimenez.operacion.fuego.quasar.services;

import com.jimenez.operacion.fuego.quasar.models.dao.PositionDao;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ComunicacionService implements IComunicacionService
{
    public PositionDao getLocation(float... distances)
    {
        double xA = -500.0;
        double yA = -200.0;

        double xB = 100.0;
        double yB = -100.0;

        double xC = 500.0;
        double yC = 100.0;

        // pérdida de precisión, ya que los double tienen más bits de precisión que los float
        int longitud = distances.length;
        double[] arrayDouble = new double[longitud];

        for (int i = 0; i < longitud; i++) {
            arrayDouble[i] = distances[i];
        }

        double[][] positions = new double[][] {{ xA, yA }, { xB, yB }, { xC, yC }};

        NonLinearLeastSquaresSolver trilateracion = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, arrayDouble), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = trilateracion.solve();
        double[] centroid = optimum.getPoint().toArray();

        return new PositionDao((float)centroid[0], (float)centroid[1]);
    }

    public String getMessage(String[]... messages)
    {
        System.out.println(Arrays.deepToString(messages));
        return "message a descubrir";
    }
}