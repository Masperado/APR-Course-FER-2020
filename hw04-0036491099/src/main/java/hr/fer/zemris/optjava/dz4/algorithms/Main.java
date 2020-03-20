package hr.fer.zemris.optjava.dz4.algorithms;

import hr.fer.zemris.optjava.dz4.api.ICrossing;
import hr.fer.zemris.optjava.dz4.api.IFunction;
import hr.fer.zemris.optjava.dz4.api.IMutation;
import hr.fer.zemris.optjava.dz4.api.ISelection;
import hr.fer.zemris.optjava.dz4.impl.*;

import java.util.Random;


/*
1. zadatak
- double  1. i 2. - veća mutacija 0.2
- double 3. - veća mutacija 0.2
- double 4. - veća mutacija 0.2
- binarni 1. i 2. - mala mutacija 0.05
- binarni 3. - mala mutacija 0.05
- binarni 4. - mala mutacija 0.05

2. zadatak
- logično lošije što ima više varijabli

3. zadatak
- bolje radi binarni

4. zadatak
- bolja je veća populacija i veća mutacija

5. zadatak
- bolji je veći turnir


 */
public class Main {

    public static final double minError = 1E-6;
    public static final int maxIterations = 10000;
    public static final boolean doubleRep = true;
    public static final int populationSize = 200;
    public static final double rateOfMutation = 0.9;
    public static final boolean elite = true;
    public static final boolean elimination = false;
    public static final int functionVariables = 2;
    public static final int dg = -50;
    public static final int gg = 150;
    public static final int decimals = 4;
    public static final int precision = (int) Math.ceil(Math.log((gg - dg) * Math.pow(10, decimals) + 1) / Math.log(2));


    public static void main(String[] args) {

        if (doubleRep) {
            double[][] startingPopulation = generatePopulationDouble(populationSize, functionVariables);

//        ISelection<double[]> selection = new RouletteWheelDouble();

            ISelection<double[]> selection = new NTournamentDoubleArray(8);

//        ICrossing<double[]> crossing = new Arithmetic();

            ICrossing<double[]> crossing = new BLX(0.1);

            IMutation<double[]> mutation = new SigmaMutation(rateOfMutation);

            IFunction<double[]> function = Functions.function1;

            if (elimination) {
                EliminationAlgorithm<double[]> algorithm = new EliminationAlgorithm<>(startingPopulation,
                        function,
                        crossing, mutation, selection, minError, maxIterations);

                algorithm.run();
            } else {
                GenerationAlgorithm<double[]> algorithm = new GenerationAlgorithm<>(startingPopulation, function,
                        crossing, mutation, selection, elite, minError, maxIterations);
                algorithm.run();
            }

        } else {
            int[][][] startingPopulation = generatePopulationInt(populationSize, functionVariables);

//            ISelection<int[][]> selection = new RouletteWheelInt();

            ISelection<int[][]> selection = new NTournamentIntArray(8);

//            ICrossing<int[][]> crossing = new OnePoint();

            ICrossing<int[][]> crossing = new TwoPoint();

            IMutation<int[][]> mutation = new RateMutation(rateOfMutation);

            IFunction<int[][]> function = new IntWrappingFunction(Functions.function6, dg, gg);

            if (elimination) {
                EliminationAlgorithm<int[][]> algorithm = new EliminationAlgorithm<>(startingPopulation,
                        function,
                        crossing, mutation, selection, minError, maxIterations);

                algorithm.run();
            } else {
                GenerationAlgorithm<int[][]> algorithm = new GenerationAlgorithm<>(startingPopulation,
                        function,
                        crossing, mutation, selection, elite, minError, maxIterations);
                algorithm.run();
            }
        }


    }

    private static int[][][] generatePopulationInt(int populationSize, int functionVariables) {
        int[][][] population = new int[populationSize][functionVariables][precision];
        for (int i = 0; i < populationSize; i++) {
            population[i] = generateSolutionInt();
        }
        return population;
    }

    private static int[][] generateSolutionInt() {
        Random rand = new Random();
        int[][] solution = new int[functionVariables][precision];
        for (int i = 0; i < functionVariables; i++) {
            for (int j = 0; j < precision; j++) {
                if (rand.nextBoolean()) {
                    solution[i][j] = 1;
                } else {
                    solution[i][j] = 0;
                }
            }
        }
        return solution;
    }

    private static double[][] generatePopulationDouble(int populationSize, int functionVariables) {
        double[][] population = new double[populationSize][functionVariables];
        for (int i = 0; i < populationSize; i++) {
            population[i] = generateSolutionDouble();
        }
        return population;
    }

    private static double[] generateSolutionDouble() {
        Random rand = new Random();
        double[] solution = new double[functionVariables];
        for (int i = 0; i < functionVariables; i++) {
            solution[i] = dg + rand.nextDouble() * gg;
        }
        return solution;
    }

}
