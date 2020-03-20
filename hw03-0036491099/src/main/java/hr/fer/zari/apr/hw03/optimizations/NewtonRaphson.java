package hr.fer.zari.apr.hw03.optimizations;

import hr.fer.zari.apr.hw03.GoalFunction;
import hr.fer.zari.apr.hw03.Matrix;
import hr.fer.zari.apr.hw03.Optimization;
import hr.fer.zari.apr.hw03.Solution;

public class NewtonRaphson implements Optimization {

    private static final double eps = 1E-6;
    private boolean optimal = true;

    private final GoalFunction goalFunction;

    public NewtonRaphson(GoalFunction goalFunction, boolean optimal) {
        this.goalFunction = goalFunction;
        this.optimal = optimal;
    }


    @Override
    public Solution solve(Solution start) {

        Solution result = start.copy();

        goalFunction.calculateGradient(result);

        goalFunction.calculateHesse(result);

        goalFunction.calculate(result);

        double output = result.getOutput();
        int iterCount = 0;

        while (Matrix.mul(Matrix.inverse(result.getHesse()), result.getGradient()).norm() > eps) {

            Matrix matrix = new Matrix(result.getInputs().size(), 1);
            for (int i = 0; i < result.getInputs().size(); i++) {
                matrix.setElement(result.getInputs().get(i), i, 0);
            }

            double lamb = 1;

            if (optimal) {

                final Solution resultFinal = result.copy();
                goalFunction.calculateGradient(resultFinal);
                goalFunction.calculateHesse(resultFinal);

                GoalFunction g = new GoalFunction() {
                    @Override
                    public void calculate(Solution solution) {
                    }

                    @Override
                    public void calculateGradient(Solution solution) {

                    }

                    @Override
                    public void calculateHesse(Solution solution) {

                    }

                    @Override
                    public double calculate(double x) {

                        Matrix m = Matrix.add(matrix, Matrix.mulWithScalar(Matrix.normalize(Matrix.mul(Matrix.inverse(resultFinal.getHesse()), resultFinal.getGradient())), -x));

                        Solution temp = new Solution(m);

                        goalFunction.calculate(temp);

                        return temp.getOutput();
                    }

                    @Override
                    public int getNumberOfCalls() {
                        return 0;
                    }

                    @Override
                    public int getNumberOfCallsGrad() {
                        return 0;
                    }

                    @Override
                    public int getNumberOfCallsHesse() {
                        return 0;
                    }
                };

                Optimization opt = new ZlatniRez(g);
                Solution sol = opt.solve(result);

                lamb = sol.getX(0);

            }

            Matrix m = Matrix.add(matrix, Matrix.mulWithScalar(Matrix.normalize(Matrix.mul(Matrix.inverse(result.getHesse()), result.getGradient())), -lamb));

            result = new Solution(m);

            goalFunction.calculateGradient(result);

            goalFunction.calculateHesse(result);

            goalFunction.calculate(result);

            if (result.getOutput() < output) {
                output = result.getOutput();
            } else {
                iterCount++;
                if (iterCount == 1000) {
                    break;
                }
            }

        }

        return result;
    }

    @Override
    public Solution solve(double a, double b) {
        return solve(new Solution(a, b));
    }

    @Override
    public void setPomak(double pomak) {

    }


}
