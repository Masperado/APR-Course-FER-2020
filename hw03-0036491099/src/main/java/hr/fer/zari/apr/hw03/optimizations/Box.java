package hr.fer.zari.apr.hw03.optimizations;

import hr.fer.zari.apr.hw03.GoalFunction;
import hr.fer.zari.apr.hw03.Optimization;
import hr.fer.zari.apr.hw03.Solution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Box implements Optimization {

    private final GoalFunction goalFunction;
    private final double e = 1E-6;
    private final double alfa = 1.3;

    private final List<Predicate<Solution>> implicits;
    private final Predicate<Solution> eksplicit;

    private final List<Double> xd;
    private final List<Double> xg;

    public Box(GoalFunction goalFunction, List<Predicate<Solution>> implicits, List<Double> xd, List<Double> xg) {
        this.goalFunction = goalFunction;
        this.implicits = implicits;
        this.xd = xd;
        this.xg = xg;

        this.eksplicit = solution -> {

            for (int i = 0; i < solution.getInputs().size(); i++) {
                double input = solution.getInputs().get(i);
                if (input < xd.get(i) || input > xg.get(i)) {
                    return false;
                }

            }

            return true;
        };
    }

    @Override
    public Solution solve(Solution start) {

        for (var imp : implicits) {
            if (!imp.test(start)) {
                return start;
            }
        }

        if (!eksplicit.test(start)) {
            return start;
        }

        Solution xc = start.copy();

        List<Solution> simplex = generateSimplex(xc);


        simplex.forEach(goalFunction::calculate);


        while (simplex.get(0).distance(simplex.get(simplex.size() - 1)) >= e) {
            simplex.forEach(goalFunction::calculate);

            simplex.sort(Comparator.comparingDouble(Solution::getOutput));

            xc = centroid(simplex);

            Solution xr = refleksija(xc, simplex.get(simplex.size() - 1));

            for (int i = 0; i < xr.getInputs().size(); i++) {
                if (xr.getInputs().get(i) < xd.get(i)) {
                    xr.getInputs().set(i, xd.get(i));
                } else if (xr.getInputs().get(i) > xg.get(i)) {
                    xr.getInputs().set(i, xg.get(i));
                }
            }

            goalFunction.calculate(xr);


            while (true) {
                boolean flag = true;

                for (var imp : implicits) {
                    if (!imp.test(xr)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    break;
                }

                for (int j = 0; j < xr.getInputs().size(); j++) {
                    xr.getInputs().set(j, 0.5 * (xr.getX(j) + xc.getX(j)));
                }

            }

            goalFunction.calculate(xr);

            if (xr.getOutput() > simplex.get(simplex.size() - 2).getOutput()) {
                for (int j = 0; j < xr.getInputs().size(); j++) {
                    xr.getInputs().set(j, 0.5 * (xr.getX(j) + xc.getX(j)));
                }
            }

            if (xr.distance(simplex.get(simplex.size() - 1)) < e) {
                break;
            }

            simplex.set(simplex.size() - 1, xr);

        }

        return simplex.get(0);

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

    private List<Solution> generateSimplex(Solution xc) {

        List<Solution> simplex = new ArrayList<>();

        simplex.add(xc);

        for (int t = 0; t < xc.getInputs().size() * 2; t++) {
            List<Double> inputs = new ArrayList<>();
            for (int i = 0; i < xc.getInputs().size(); i++) {
                double R = Math.random();
                inputs.add(xd.get(i) + R * (xg.get(i) - xd.get(i)));
            }
            Solution temp = new Solution(inputs);

            while (true) {
                boolean flag = true;

                for (var imp : implicits) {
                    if (!imp.test(temp)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    break;
                }

                for (int j = 0; j < temp.getInputs().size(); j++) {
                    temp.getInputs().set(j, 0.5 * (temp.getX(j) + xc.getX(j)));
                }

            }

            simplex.add(temp);

            Solution sol = simplex.get(0).copy();

            for (int i = 0; i < sol.getInputs().size(); i++) {
                sol.getInputs().set(i, (double) 0);
            }

            for (int i = 0; i < simplex.size(); i++) {
                for (int j = 0; j < sol.getInputs().size(); j++) {
                    sol.getInputs().set(j, sol.getX(j) + simplex.get(i).getX(j));
                }

            }

            for (int i = 0; i < sol.getInputs().size(); i++) {
                sol.getInputs().set(i, sol.getX(i) / (simplex.size()));
            }

            xc = sol;


        }


        return simplex;
    }

    @Override
    public Solution solve(double a, double b) {
        return solve(new Solution(a, b));
    }

    @Override
    public void setPomak(double pomak) {
    }
}
