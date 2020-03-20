package hr.fer.zari.apr.hw02.optimizations;

import hr.fer.zari.apr.hw02.GoalFunction;
import hr.fer.zari.apr.hw02.Optimization;
import hr.fer.zari.apr.hw02.Solution;

import java.util.ArrayList;
import java.util.List;

public class HookeJeeves implements Optimization {

    private final GoalFunction goalFunction;
    private final double e = 1E-6;
    private final double fdx = 0.5;

    public HookeJeeves(GoalFunction goalFunction) {
        this.goalFunction = goalFunction;
    }

    @Override
    public Solution solve(Solution start) {

        Solution xp = start.copy();
        Solution xb = start.copy();
        Solution xn;

        double dx = fdx;

        do {
            xn = explore(xp, dx);

            goalFunction.calculate(xn);
            goalFunction.calculate(xb);

            if (xn.getOutput()<xb.getOutput()){
                List<Double> xpList = new ArrayList<>();
                for (int i=0;i<xn.getInputs().size();i++){
                    xpList.add(2*xn.getX(i)-xb.getX(i));
                }
                xp = new Solution(xpList);
                xb = xn.copy();
            } else {
                dx = dx/2;
                xp = xb.copy();
            }
        } while (dx>=e);

        return xb;
    }

    private Solution explore(Solution xp, double dx) {

        Solution x = xp.copy();
        goalFunction.calculate(x);

        for (int i=0; i<x.getInputs().size();i++){
            double P = x.getOutput();

            x.getInputs().set(i,x.getX(i)+dx);

            goalFunction.calculate(x);

            double N = x.getOutput();

            if (N>P){
                x.getInputs().set(i,x.getX(i)-2*dx);
                goalFunction.calculate(x);
                N = x.getOutput();
                if (N>P){
                    x.getInputs().set(i,x.getX(i)+dx);
                }
            }
        }

        return x;
    }

    @Override
    public Solution solve(double a, double b) {
        return solve(new Solution(a,b));
    }

    @Override
    public void setPomak(double pomak) {

    }
}
