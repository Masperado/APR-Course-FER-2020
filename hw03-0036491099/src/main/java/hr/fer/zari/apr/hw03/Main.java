package hr.fer.zari.apr.hw03;

import hr.fer.zari.apr.hw03.functions.F1;
import hr.fer.zari.apr.hw03.functions.F2;
import hr.fer.zari.apr.hw03.functions.F3;
import hr.fer.zari.apr.hw03.functions.F4;
import hr.fer.zari.apr.hw03.optimizations.Box;
import hr.fer.zari.apr.hw03.optimizations.Gradijentni;
import hr.fer.zari.apr.hw03.optimizations.Mjesoviti;
import hr.fer.zari.apr.hw03.optimizations.NewtonRaphson;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        first();

        second();

        third();

        fourth();

        fifth();
    }

    private static void first() {

        Solution sol = new Solution(0, 0);
        GoalFunction goalFunction = new F3();
        Optimization opt = new Gradijentni(goalFunction, true);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");

        sol = new Solution(0, 0);
        goalFunction = new F3();
        opt = new Gradijentni(goalFunction, false);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");
    }

    private static void second() {
        Solution sol = new Solution(-1.9, 2);
        GoalFunction goalFunction = new F1();
        Optimization opt = new Gradijentni(goalFunction, true);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");

        sol = new Solution(-1.9, 2);
        goalFunction = new F1();
        opt = new NewtonRaphson(goalFunction, true);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");

        sol = new Solution(0.1, 0.3);
        goalFunction = new F2();
        opt = new Gradijentni(goalFunction, true);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");

        sol = new Solution(0.1, 0.3);
        goalFunction = new F2();
        opt = new NewtonRaphson(goalFunction, true);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");
    }

    private static void third() {
        Solution sol = new Solution(-1.9, 2);
        GoalFunction goalFunction = new F1();
        Optimization opt = new Box(goalFunction, Arrays.asList(solution -> (solution.getX(1) - solution.getX(0)) >= 0, solution -> (2 - solution.getX(0)) >= 0), Arrays.asList(-100.0, -100.0), Arrays.asList(100.0, 100.0));
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");

        sol = new Solution(0.1, 0.3);
        goalFunction = new F2();
        opt = new Box(goalFunction, Arrays.asList(solution -> (solution.getX(1) - solution.getX(0)) >= 0, solution -> (2 - solution.getX(0)) >= 0), Arrays.asList(-100.0, -100.0), Arrays.asList(100.0, 100.0));
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");
    }

    private static void fourth() {
        Solution sol = new Solution(-1.9, 2);
        GoalFunction goalFunction = new F1();
        Optimization opt = new Mjesoviti(goalFunction, Arrays.asList(solution -> solution.getX(1) - solution.getX(0), solution -> 2 - solution.getX(0)), Arrays.asList());
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");

        sol = new Solution(0.1, 0.3);
        goalFunction = new F2();
        opt = new Mjesoviti(goalFunction, Arrays.asList(solution -> solution.getX(1) - solution.getX(0), solution -> 2 - solution.getX(0)), Arrays.asList());
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");
    }

    private static void fifth() {
        Solution sol = new Solution(5, 5);
        GoalFunction goalFunction = new F4();
        Optimization opt = new Mjesoviti(goalFunction, Arrays.asList(solution -> 3 - solution.getX(0) - solution.getX(1), solution -> 3 + 1.5 * solution.getX(0) - solution.getX(1)), Arrays.asList(solution -> solution.getX(1) - 1));
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberOfCalls());
        System.out.println(goalFunction.getNumberOfCallsGrad());
        System.out.println(goalFunction.getNumberOfCallsHesse());

        System.out.println("-----");
    }


}
