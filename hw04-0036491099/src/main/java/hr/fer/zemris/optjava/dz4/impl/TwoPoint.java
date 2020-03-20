package hr.fer.zemris.optjava.dz4.impl;

import hr.fer.zemris.optjava.dz4.api.ICrossing;

public class TwoPoint implements ICrossing<int[][]> {

    @Override
    public int[][][] cross(int[][] parent1, int[][] parent2) {

        int[][] kid1 = new int[parent1.length][parent1[0].length];
        int[][] kid2 = new int[parent2.length][parent1[0].length];

        int pointIndex1 = (int) (Math.random() * parent1.length*parent1[0].length);
        int pointIndex2 = (int) (Math.random() * parent1.length*parent1[0].length);

        int currentIndex = 0;

        if (pointIndex2<pointIndex1){
            int temp = pointIndex1;
            pointIndex1 = pointIndex2;
            pointIndex2 = temp;
        }


        for (int i = 0; i < parent1.length; i++) {
            for (int j = 0; j < parent1[0].length; j++) {
                if (currentIndex < pointIndex1) {
                    kid1[i][j] = parent1[i][j];
                    kid2[i][j] = parent1[i][j];
                } else if (currentIndex<pointIndex2){
                    kid1[i][j] = parent2[i][j];
                    kid2[i][j] = parent1[i][j];
                } else {
                    kid1[i][j] = parent1[i][j];
                    kid2[i][j] = parent1[i][j];
                }
                currentIndex++;
            }
        }

        return new int[][][]{kid1, kid2};
    }
}
