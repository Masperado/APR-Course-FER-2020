package hr.fer.zari.apr.hw02.functions;

import hr.fer.zari.apr.hw02.GoalFunction;
import hr.fer.zari.apr.hw02.Solution;


public class F6 implements GoalFunction {

    private int numberOfCalls = 0;

    @Override
    public void calculate(Solution solution) {
        double output = 0.5;

        double sumSq= 0;
        for (int i=0; i<solution.getInputs().size();i++){
            double xi = solution.getX(i);
            sumSq+= xi*xi;
        }

        output+= (Math.pow(Math.sin(Math.sqrt(sumSq)),2)-0.5)/(Math.pow(1+0.001*sumSq,2));

        solution.setOutput(output);
        numberOfCalls++;

    }

    public double calculate(double x) {
        double sumSq = x*x;
        numberOfCalls++;
        return 0.5 + (Math.pow(Math.sin(Math.sqrt(sumSq)),2)-0.5)/(Math.pow(1+0.001*sumSq,2));
    }

    @Override
    public int getNumberofCalls() {
        return numberOfCalls;
    }
}
