package hr.fer.zari.apr.hw02.optimizations;

import hr.fer.zari.apr.hw02.GoalFunction;
import hr.fer.zari.apr.hw02.Optimization;
import hr.fer.zari.apr.hw02.Solution;

public class Koordinatni implements Optimization {

    private final GoalFunction goalFunction;
    private final double e = 1E-6;

    public Koordinatni(GoalFunction goalFunction) {
        this.goalFunction = goalFunction;
    }

    @Override
    public Solution solve(Solution start) {

        Solution x = start.copy();
        Solution xs;

        do {
            xs = x.copy();
            for (int i=0;i<xs.getInputs().size();i++){

                final Solution xsFinal = x.copy();
                final int iFinal = i;
                GoalFunction g = new GoalFunction() {
                    @Override
                    public void calculate(Solution solution) {
                    }

                    @Override
                    public double calculate(double x) {
                        Solution temp = xsFinal.copy();

                        temp.getInputs().set(iFinal,temp.getX(iFinal)+x);

                        goalFunction.calculate(temp);

                        return temp.getOutput();
                    }

                    @Override
                    public int getNumberofCalls() {
                        return 0;
                    }
                };

                Optimization opt = new ZlatniRez(g);
                Solution sol = opt.solve(x);

                double lamb = sol.getX(0);

                x.getInputs().set(iFinal,x.getX(iFinal)+lamb);

            }
        } while (x.distance(xs)>=e);

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
