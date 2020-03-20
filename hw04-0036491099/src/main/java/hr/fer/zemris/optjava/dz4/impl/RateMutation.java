package hr.fer.zemris.optjava.dz4.impl;

import hr.fer.zemris.optjava.dz4.api.IMutation;

public class RateMutation implements IMutation<int[][]> {

	private double rate;

	public RateMutation(double rate) {
		this.rate = rate;
	}

	@Override
	public int[][][] mutate(int[][][] kids) {
		return new int[][][] { mutateKid(kids[0]), mutateKid(kids[1]) };
	}

	private int[][] mutateKid(int[][] kid) {
		int[][] mutant = kid.clone();

		for (int i = 0; i < kid.length; i++) {
			for (int j=0;j<kid[0].length;j++) {
				if (Math.random() < rate) {
					kid[i][j] = 1 - kid[i][j];
				}
			}
		}
		return mutant;
	}

}