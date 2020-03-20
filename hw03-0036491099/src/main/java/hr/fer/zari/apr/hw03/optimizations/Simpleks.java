package hr.fer.zari.apr.hw03.optimizations;

import hr.fer.zari.apr.hw03.GoalFunction;
import hr.fer.zari.apr.hw03.Optimization;
import hr.fer.zari.apr.hw03.Solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Simpleks implements Optimization {

    private final GoalFunction goalFunction;
    private final double e = 1E-6;
    private final double alfa = 1;
    private final double beta = 0.5;
    private final double gamma = 2;
    private final double sigma = 0.5;
    private double pomak = 1;

    public Simpleks(GoalFunction goalFunction) {
        this.goalFunction = goalFunction;
    }

    @Override
    public Solution solve(Solution start) {

        List<Solution> simplex = generateSimplex(start);
        simplex.forEach(goalFunction::calculate);


        while (simplex.get(0).distance(simplex.get(simplex.size() - 1)) >= e) {
            simplex.forEach(goalFunction::calculate);

            simplex.sort(Comparator.comparingDouble(Solution::getOutput));

            Solution xc = centroid(simplex);

            Solution xr = refleksija(xc, simplex.get(simplex.size() - 1));

            goalFunction.calculate(xr);

            if (xr.getOutput() < simplex.get(0).getOutput()) {
                Solution xe = ekspanzija(xc, xr);

                goalFunction.calculate(xe);

                if (xe.getOutput() < simplex.get(0).getOutput()) {
                    simplex.set(simplex.size() - 1, xe);
                } else {
                    simplex.set(simplex.size() - 1, xr);
                }

            } else {
                if (refleksijaVeca(xr, simplex)) {
                    if (xr.getOutput() < simplex.get(simplex.size() - 1).getOutput()) {
                        simplex.set(simplex.size() - 1, xr);
                    }
                    Solution xk = kontrakcija(xc, simplex.get(simplex.size() - 1));
                    goalFunction.calculate(xk);
                    if (xk.getOutput() < simplex.get(simplex.size() - 1).getOutput()) {
                        simplex.set(simplex.size() - 1, xk);
                    } else {
                        sazmiSimplex(simplex);
                    }
                } else {
                    simplex.set(simplex.size() - 1, xr);
                }
            }
        }

        return simplex.get(0);

    }

    private void sazmiSimplex(List<Solution> simplex) {

        Solution xl = simplex.get(0);

        for (int i = 1; i < simplex.size(); i++) {
            Solution temp = simplex.get(i).copy();

            for (int j = 0; j < temp.getInputs().size(); j++) {
                temp.getInputs().set(j, sigma * (temp.getX(j) + xl.getX(j)));
            }

            simplex.set(i, temp);

        }

    }

    private boolean refleksijaVeca(Solution xr, List<Solution> simplex) {
        for (int i = 0; i < simplex.size() - 1; i++) {
            if (xr.getOutput() < simplex.get(i).getOutput()) {
                return false;
            }
        }

        return true;
    }

    private Solution kontrakcija(Solution xc, Solution xh) {
        Solution sol = xc.copy();

        for (int i = 0; i < sol.getInputs().size(); i++) {
            sol.getInputs().set(i, (1 - beta) * xc.getX(i) + beta * xh.getX(i));
        }

        return sol;
    }

    private Solution ekspanzija(Solution xc, Solution xr) {

        Solution sol = xc.copy();

        for (int i = 0; i < sol.getInputs().size(); i++) {
            sol.getInputs().set(i, (1 - gamma) * xc.getX(i) + gamma * xr.getX(i));
        }

        return sol;
    }

    private Solution refleksija(Solution xc, Solution xh) {

        Solution sol = xc.copy();

        for (int i = 0; i < sol.getInputs().size(); i++) {
            sol.getInputs().set(i, (1 + alfa) * xc.getX(i) - alfa * xh.getX(i));
        }

        return sol;

    }

    private Solution centroid(List<Solution> simplex) {

        Solution sol = simplex.get(0).copy();

        for (int i = 0; i < sol.getInputs().size(); i++) {
            sol.getInputs().set(i, (double) 0);
        }

        for (int i = 0; i < simplex.size() - 1; i++) {
            for (int j = 0; j < sol.getInputs().size(); j++) {
                sol.getInputs().set(j, sol.getX(j) + simplex.get(i).getX(j));
            }

        }

        for (int i = 0; i < sol.getInputs().size(); i++) {
            sol.getInputs().set(i, sol.getX(i) / (simplex.size() - 1));
        }

        return sol;
    }

    private List<Solution> generateSimplex(Solution start) {

        List<Solution> simplex = new ArrayList<>();

        simplex.add(start);

        for (int i = 0; i < start.getInputs().size(); i++) {
            Solution temp = start.copy();

            temp.getInputs().set(i, start.getX(i) + pomak);

            simplex.add(temp);

        }

        return simplex;
    }

    @Override
    public Solution solve(double a, double b) {
        return solve(new Solution(a, b));
    }

    @Override
    public void setPomak(double pomak) {
        this.pomak = pomak;
    }
}
