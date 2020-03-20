package hr.fer.zemris.optjava.dz4.impl;

import hr.fer.zemris.optjava.dz4.api.ISelection;

public class RouletteWheelInt implements ISelection<int[][]> {

	@Override
	public int[][][] select(int[][][] population, double[] values) {
		int[][] parent1 = selectParent(population, values);
		int[][] parent2 = selectParent(population, values);

		while (same(parent1,parent2)) {
			parent2 = selectParent(population, values);
		}

		return new int[][][] { parent1, parent2 };
	}

	public int[][] selectParent(int[][][] population, double[] values) {
		int worstIndex = findWorst(values);
		double[] rouletteValues = scale(values.clone(), values[worstIndex]);
		double sum = sum(rouletteValues);
		double hit = Math.random() * sum;
		int hitIndex = findIndex(rouletteValues, hit);
		if (hitIndex == 6) {
			hitIndex = 5;
		}
		return population[hitIndex];

	}


	private double sum(double[] values) {
		double sum = 0;

		for (int i = 0; i < values.length; i++) {
			sum += values[i];
		}

		return sum;
	}

	private int findIndex(double[] rouletteValues, double hit) {
		int index = 0;
		double sum = 0;

		for (int i = 0; i < rouletteValues.length; i++) {

			sum += rouletteValues[i];
			if (sum > hit) {
				break;
			} else {
				index++;
			}
		}

		return index;
	}

	private boolean same(int[][] parent1, int parent2[][]) {
		for (int i=0;i<parent1.length;i++){
			for (int j=0;j<parent2.length;j++) {
				if (parent1[i][j] != parent2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	private double[] scale(double[] values, double worstValue) {
		for (int i = 0; i < values.length; i++) {
			values[i] = worstValue - values[i];
		}
		return values;
	}

	private int findWorst(double[] values) {
		int worstIndex = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] > values[worstIndex]) {
				worstIndex = i;
			}
		}
		return worstIndex;
	}

}
