package hr.fer.zari.apr.hw03.functions;

import hr.fer.zari.apr.hw03.GoalFunction;
import hr.fer.zari.apr.hw03.Matrix;
import hr.fer.zari.apr.hw03.Solution;


public class F1 implements GoalFunction {

    private int numberOfCalls = 0;
    private int numberOfCallsGrad = 0;
    private int numberOfCallsHesse = 0;

    @Override
    public void calculate(Solution solution) {
        double x2 = solution.getX(1);
        double x1 = solution.getX(0);

        double output = 100 * Math.pow(x2 - x1 * x1, 2) + Math.pow(1 - x1, 2);

        solution.setOutput(output);
        numberOfCalls++;

    }

    @Override
    public void calculateGradient(Solution solution) {
        double x2 = solution.getX(1);
        double x1 = solution.getX(0);

        double pox1 = 400 * x1 * x1 * x1 - 400 * x1 * x2 + 2 * x1 - 2;
        double pox2 = 200 * (x2 - x1 * x1);

        Matrix gradient = new Matrix(2, 1);

        gradient.setElement(pox1, 0, 0);
        gradient.setElement(pox2, 1, 0);

        solution.setGradient(gradient);
        numberOfCallsGrad++;

    }

    @Override
    public void calculateHesse(Solution solution) {
        double x2 = solution.getX(1);
        double x1 = solution.getX(0);

        double pox1x1 = 1200 * x1 * x1 - 400 * x2 + 2;
//        double pox1x1 = -400*(x2-x1*x1)+800*x1*x1+2;
        double pox1x2 = -400 * x1;
        double pox2x1 = -400 * x1;
        double pox2x2 = 200;

        Matrix hesse = new Matrix(2, 2);
        hesse.setElement(pox1x1, 0, 0);
        hesse.setElement(pox1x2, 0, 1);
        hesse.setElement(pox2x1, 1, 0);
        hesse.setElement(pox2x2, 1, 1);

        solution.setHesse(hesse);
        numberOfCallsHesse++;

    }

    @Override
    public double calculate(double x) {
        numberOfCalls++;
        return 0;
    }


    @Override
    public int getNumberOfCalls() {
        return numberOfCalls;
    }

    @Override
    public int getNumberOfCallsGrad() {
        return numberOfCallsGrad;
    }

    @Override
    public int getNumberOfCallsHesse() {
        return numberOfCallsHesse;
    }
}
