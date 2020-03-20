package hr.fer.zari.apr.hw03.optimizations;

import hr.fer.zari.apr.hw03.GoalFunction;
import hr.fer.zari.apr.hw03.Optimization;
import hr.fer.zari.apr.hw03.Solution;

import java.util.List;
import java.util.function.Function;

public class Mjesoviti implements Optimization {

    private final GoalFunction goalFunction;
    private final double e = 1E-6;

    private final List<Function<Solution, Double>> nejedn;
    private final List<Function<Solution, Double>> jedn;

    private double r = 1.0;

    public Mjesoviti(GoalFunction goalFunction, List<Function<Solution, Double>> nejedn, List<Function<Solution, Double>> jedn) {
        this.goalFunction = goalFunction;
        this.nejedn = nejedn;
        this.jedn = jedn;
    }

    @Override
    public Solution solve(Solution start) {

        for (var nejed : nejedn) {
            if (nejed.apply(start) < 0) {
                start = generateStart(start);
                break;
            }
        }

        Solution cur = start.copy();

        Solution next = cur.copy();

        do {
            cur = next.copy();

            GoalFunction temp = new GoalFunction() {
                @Override
                public void calculate(Solution solution) {

                    goalFunction.calculate(solution);

                    double sum = 0;
                    for (var nej : nejedn) {
                        double value = nej.apply(solution);
                        if (value < 0) {
                            value = -Double.MAX_VALUE;
                        } else {
                            value = Math.log(value);
                        }
                        sum += value;
                    }

                    solution.setOutput(solution.getOutput() - r * sum);

                    sum = 0;
                    for (var jed : jedn) {
                        double value = jed.apply(solution);
                        sum += value * value;
                    }

                    solution.setOutput(solution.getOutput() + (1.0 / r) * sum);


                }

                @Override
                public void calculateGradient(Solution solution) {

                }

                @Override
                public void calculateHesse(Solution solution) {

                }

                @Override
                public double calculate(double x) {
                    return 0;
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

            Optimization opt = new Simpleks(temp);

            next = opt.solve(cur);

            r = r / 10;

        } while (cur.distance(next) >= e);

        return next;

    }

    private Solution generateStart(Solution start) {
        GoalFunction temp = new GoalFunction() {
            @Override
            public void calculate(Solution solution) {

                double sum = 0;
                for (var nej : nejedn) {
                    double value = nej.apply(solution);

                    if (value >= 0) {
                        value = 0;
                    }

                    sum += -value;
                }

                solution.setOutput(sum);

            }

            @Override
            public void calculateGradient(Solution solution) {

            }

            @Override
            public void calculateHesse(Solution solution) {

            }

            @Override
            public double calculate(double x) {
                return 0;
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

        Optimization opt = new Simpleks(temp);

        return opt.solve(start);

    }


    public Solution solve(double a, double b) {
        return solve(new Solution(a, b));
    }

    @Override
    public void setPomak(double pomak) {
    }
}
