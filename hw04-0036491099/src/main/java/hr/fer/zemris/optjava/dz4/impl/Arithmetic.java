package hr.fer.zemris.optjava.dz4.impl;

import hr.fer.zemris.optjava.dz4.api.ICrossing;

public class Arithmetic implements ICrossing<double[]> {


	public Arithmetic() {
	}

	@Override
	public double[][] cross(double[] parent1, double[] parent2) {
		double[] firstChild = generateChild(parent1, parent2);
		double[] secondChild = generateChild(parent1, parent2);
		return new double[][] { firstChild, secondChild };
	}

	private double[] generateChild(double[] parent1, double[] parent2) {
		double[] child = new double[parent1.length];

		for (int i = 0; i < parent1.length; i++) {

			double alfa = Math.random();

			child[i] = alfa*parent1[i]+(1-alfa)*parent2[i];

		}
		return child;

	}
}
