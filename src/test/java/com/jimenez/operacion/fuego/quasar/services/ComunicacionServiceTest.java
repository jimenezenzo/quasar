package com.jimenez.operacion.fuego.quasar.services;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComunicacionServiceTest {

    @Test
    public void testCalculoDeCoordenada()
    {
        double x1 = -500.0;
        double y1 = -200.0;
        double d1 = 100.0;

        double x2 = 100.0;
        double y2 = -100.0;
        double d2 = 115.5;

        double x3 = 500.0;
        double y3 = 100.0;
        double d3 = 142.7;

        /// Resolver el sistema de ecuaciones
        double A[][] = {{2 * (x2 - x1), 2 * (y2 - y1)},
                {2 * (x3 - x1), 2 * (y3 - y1)}};
        double B[] = {d1 * d1 - d2 * d2 - x1 * x1 + x2 * x2 - y1 * y1 + y2 * y2,
                d1 * d1 - d3 * d3 - x1 * x1 + x3 * x3 - y1 * y1 + y3 * y3};

        int n = B.length;

        // Eliminación hacia adelante
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double factor = A[i][k] / A[k][k];
                for (int j = k; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                }
                B[i] -= factor * B[k];
            }
        }

        // Sustitución hacia atrás
        double[] solucion = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * solucion[j];
            }
            solucion[i] = (B[i] - sum) / A[i][i];
        }


        assertEquals(-100.0, solucion[0]);
        //assertEquals(75.5, centroid[1]);
    }
}
