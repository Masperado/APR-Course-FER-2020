package hr.fer.zari.apr.hw02.functions;

import hr.fer.zari.apr.hw02.GoalFunction;
import hr.fer.zari.apr.hw02.Solution;


public class F3 implements GoalFunction {

    private int numberOfCalls = 0;

    @Override
    public void calculate(Solution solution) {
        double output = 0;

        for (int i=0; i<solution.getInputs().size();i++){
            output+= Math.pow(solution.getX(i)-i-1,2);
        }

        solution.setOutput(output);
        numberOfCalls++;

    }

    @Override
    public double calculate(double x) {
        numberOfCalls++;
        return Math.pow(x-1,2);
    }

    @Override
    public int getNumberofCalls() {
        return numberOfCalls;
    }
}
