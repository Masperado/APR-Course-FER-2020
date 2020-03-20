package hr.fer.zari.apr.hw02;

import hr.fer.zari.apr.hw02.functions.*;
import hr.fer.zari.apr.hw02.optimizations.HookeJeeves;
import hr.fer.zari.apr.hw02.optimizations.Koordinatni;
import hr.fer.zari.apr.hw02.optimizations.Simpleks;
import hr.fer.zari.apr.hw02.optimizations.ZlatniRez;

public class Main {

    public static void main(String[] args) {
//        first();
//
//        second();
//
//        third();
//
//        fourth();
//
        fifth();
    }

    private static void first() {
        Solution sol = new Solution(10);
        GoalFunction goalFunction = new F3();
        Optimization opt = new ZlatniRez(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

        System.out.println("-----");

        sol = new Solution(10);
        goalFunction = new F3();
        opt = new Koordinatni(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

        System.out.println("-----");

        sol = new Solution(10);
        goalFunction = new F3();
        opt = new HookeJeeves(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

        System.out.println("-----");

        sol = new Solution(10);
        goalFunction = new F3();
        opt = new Simpleks(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());
    }

    private static void second(){
        Solution sol = new Solution(-1.9,2);
        GoalFunction goalFunction = new F1();
        Optimization opt = new Koordinatni(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

        System.out.println("-----");

        sol = new Solution(0.1,0.3);
        goalFunction = new F2();
        opt = new Koordinatni(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());


        System.out.println("-----");

        sol = new Solution(0,0,0,0,0);
        goalFunction = new F3();
        opt = new Koordinatni(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());


        System.out.println("-----");

        sol = new Solution(5.1,1.1);
        goalFunction = new F4();
        opt = new Koordinatni(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

        System.out.println("-----");

        sol = new Solution(-1.9,2);
        goalFunction = new F1();
        opt = new Simpleks(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

        System.out.println("-----");

        sol = new Solution(0.1,0.3);
        goalFunction = new F2();
        opt = new Simpleks(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());


        System.out.println("-----");

        sol = new Solution(0,0,0,0,0);
        goalFunction = new F3();
        opt = new Simpleks(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());


        System.out.println("-----");

        sol = new Solution(5.1,1.1);
        goalFunction = new F4();
        opt = new Simpleks(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

        System.out.println("-----");

        sol = new Solution(-1.9,2);
        goalFunction = new F1();
        opt = new HookeJeeves(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

        System.out.println("-----");

        sol = new Solution(0.1,0.3);
        goalFunction = new F2();
        opt = new HookeJeeves(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());


        System.out.println("-----");

        sol = new Solution(0,0,0,0,0);
        goalFunction = new F3();
        opt = new HookeJeeves(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());


        System.out.println("-----");

        sol = new Solution(5.1,1.1);
        goalFunction = new F4();
        opt = new HookeJeeves(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

    }

    private static void third(){
        Solution sol = new Solution(5,5);
        GoalFunction goalFunction = new F4();
        Optimization opt = new HookeJeeves(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());

        System.out.println("-----");

        sol = new Solution(5,5);
        goalFunction = new F4();
        opt = new Simpleks(goalFunction);
        System.out.println(opt.solve(sol));
        System.out.println(goalFunction.getNumberofCalls());
    }

    private static void fourth(){

        for (int i=1;i<=20;i++){
            Solution sol = new Solution(0.5,0.5);
            GoalFunction goalFunction = new F1();
            Optimization opt = new Simpleks(goalFunction);
            opt.setPomak(i);
            System.out.println(opt.solve(sol));
            System.out.println(goalFunction.getNumberofCalls());

            System.out.println("-----");
        }
        System.out.println("--------------");


        for (int i=1;i<=20;i++){
            Solution sol = new Solution(20,20);
            GoalFunction goalFunction = new F1();
            Optimization opt = new Simpleks(goalFunction);
            opt.setPomak(i);
            System.out.println(opt.solve(sol));
            System.out.println(goalFunction.getNumberofCalls());

            System.out.println("-----");
        }

    }

    private static void fifth(){

        int cnt = 0;

        for (int i=1;i<=100000;i++){
            Solution sol = new Solution(-50+Math.random()*100,-50+Math.random()*100);
            GoalFunction goalFunction = new F6();
            Optimization opt = new Simpleks(goalFunction);

            Solution solved = opt.solve(sol);
            System.out.println(solved);
            System.out.println(goalFunction.getNumberofCalls());

            if (solved.getOutput()<1E-4){
                cnt++;
            }

            System.out.println("-----");
        }

        System.out.println((double)cnt/100000);
    }
}
