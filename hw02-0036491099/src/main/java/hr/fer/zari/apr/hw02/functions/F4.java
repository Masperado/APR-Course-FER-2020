package hr.fer.zari.apr.hw02.functions;

import hr.fer.zari.apr.hw02.GoalFunction;
import hr.fer.zari.apr.hw02.Solution;


public class F4 implements GoalFunction {

    private int numberOfCalls = 0;

    @Override
    public void calculate(Solution solution) {
        double x2 = solution.getX(1);
        double x1 = solution.getX(0);

        double output = Math.abs((x1-x2)*(x1+x2))+Math.sqrt(x1*x1+x2*x2);

        solution.setOutput(output);
        numberOfCalls++;

    }

    public double calculate(double x) {
        numberOfCalls++;
        return 0;
    }

    @Override
    public int getNumberofCalls() {
        return numberOfCalls;
    }
}
