package hr.fer.zari.apr.hw02.functions;

import hr.fer.zari.apr.hw02.GoalFunction;
import hr.fer.zari.apr.hw02.Solution;


public class F1 implements GoalFunction {

    private int numberOfCalls = 0;

    @Override
    public void calculate(Solution solution) {
        double x2 = solution.getX(1);
        double x1 = solution.getX(0);

        double output = 100*Math.pow(x2-x1*x1,2)+Math.pow(1-x1,2);

        solution.setOutput(output);
        numberOfCalls++;

    }

    @Override
    public double calculate(double x) {
        numberOfCalls++;
        return 0;
    }


    @Override
    public int getNumberofCalls() {
        return numberOfCalls;
    }
}
