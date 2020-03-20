package hr.fer.zemris.optjava.dz4.impl;

import hr.fer.zemris.optjava.dz4.api.IFunction;

public class IntWrappingFunction implements IFunction<int[][]> {


    private IFunction<double[]> function;
    private int dg;
    private int gg;

    public IntWrappingFunction(IFunction<double[]> function, int dg, int gg) {
        this.function = function;
        this.dg = dg;
        this.gg = gg;
    }

    @Override
    public double valueAt(int[][] point) {
        double[] pointDouble = new double[point.length];

        for (int i=0;i<pointDouble.length;i++){
            int[] binaryNumber = point[i];
            double number=0;
            for (int j=0; j<binaryNumber.length;j++){
                number += binaryNumber[j]*Math.pow(2,binaryNumber.length-j-1);
            }
            pointDouble[i] = dg + ((number)/(Math.pow(2,binaryNumber.length)-1))*(gg-dg);
        }

        return function.valueAt(pointDouble);
    }
}
