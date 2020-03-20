package hr.fer.zemris.optjava.dz4.impl;

import hr.fer.zemris.optjava.dz4.api.IFunction;

public class Functions {


    public static final IFunction<double[]> function1 = point -> {
        double x1 = point[0];
        double x2 = point[1];

        return 100 * Math.pow(x2 - x1 * x1, 2) + Math.pow(1 - x1, 2);
    };


    public static final IFunction<double[]> function3 = point -> {
        double sum = 0;

        for (int i = 0; i < point.length; i++) {
            sum += Math.pow(point[i] - i - 1, 2);
        }

        return sum;
    };


    public static final IFunction<double[]> function6 = point -> {
        double sumxi2 = 0;

        for (double v : point) {
            sumxi2 += Math.pow(v, 2);
        }

        return 0.5 + (Math.pow(Math.sin(Math.sqrt(sumxi2)), 2) - 0.5) / (Math.pow(1 + 0.0001 * sumxi2, 2));
    };

    public static final IFunction<double[]> function7 = point -> {
        double sumxi2 = 0;

        for (double v : point) {
            sumxi2 += Math.pow(v, 2);
        }

        return Math.pow(sumxi2, 0.25) * (1 + Math.pow(Math.sin(50 * Math.pow(sumxi2, 0.1)), 2));
    };

}
