package hr.fer.zari.apr.hw03;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {


    public static void main(String[] args) throws IOException {

//        first();

//        second();

//        third();
//
        fourth();
    }

    private static void first() throws IOException {

        Matrix xk = Matrix.fromFile(Paths.get("matrix/firstXk.txt"));
        Matrix A = Matrix.fromFile(Paths.get("matrix/firstA.txt"));
        Matrix B = Matrix.fromFile(Paths.get("matrix/firstB.txt"));

        Method m = new Euler();
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, true);
        System.out.println();

        m = new ReverseEuler();
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, true);
        System.out.println();

        m = new Trapese();
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, true);
        System.out.println();

        m = new RungeKutta();
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, true);
        System.out.println();

        m = new PredictorCorector(new Euler(), new ReverseEuler(), 2);
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, true);
        System.out.println();

        m = new PredictorCorector(new Euler(), new Trapese(), 1);
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, true);
        System.out.println();

    }

    private static void second() throws IOException {

        Matrix xk = Matrix.fromFile(Paths.get("matrix/secondXk.txt"));
        Matrix A = Matrix.fromFile(Paths.get("matrix/secondA.txt"));
        Matrix B = Matrix.fromFile(Paths.get("matrix/secondB.txt"));

        Method m = new Euler();
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 1, 0.1, false);
        System.out.println();

        m = new ReverseEuler();
        m.solve(Matrix.copy(xk), A, B, false, 0.1, 1, 0.1, false);
        System.out.println();

        m = new Trapese();
        m.solve(Matrix.copy(xk), A, B, false, 0.1, 1, 0.1, false);
        System.out.println();

        m = new RungeKutta();
        m.solve(Matrix.copy(xk), A, B, false, 0.0001, 1, 0.1, false);
        System.out.println();

        m = new PredictorCorector(new Euler(), new ReverseEuler(), 2);
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 1, 0.1, false);
        System.out.println();

        m = new PredictorCorector(new Euler(), new Trapese(), 1);
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 1, 0.1, false);
        System.out.println();

    }

    private static void third() throws IOException {

        Matrix xk = Matrix.fromFile(Paths.get("matrix/thirdXk.txt"));
        Matrix A = Matrix.fromFile(Paths.get("matrix/thirdA.txt"));
        Matrix B = Matrix.fromFile(Paths.get("matrix/thirdB.txt"));

        Method m = new Euler();
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, false);
        System.out.println();

        m = new ReverseEuler();
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, false);
        System.out.println();

        m = new Trapese();
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, false);
        System.out.println();

        m = new RungeKutta();
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, false);
        System.out.println();

        m = new PredictorCorector(new Euler(), new ReverseEuler(), 2);
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, false);
        System.out.println();

        m = new PredictorCorector(new Euler(), new Trapese(), 1);
        m.solve(Matrix.copy(xk), A, B, false, 0.01, 10, 1, false);
        System.out.println();

    }

    private static void fourth() throws IOException {

        Matrix xk = Matrix.fromFile(Paths.get("matrix/fourthXk.txt"));
        Matrix A = Matrix.fromFile(Paths.get("matrix/fourthA.txt"));
        Matrix B = Matrix.fromFile(Paths.get("matrix/fourthB.txt"));

        Method m = new Euler();
        m.solve(Matrix.copy(xk), A, B, true, 0.01, 1, 0.1, false);
        System.out.println();

        m = new ReverseEuler();
        m.solve(Matrix.copy(xk), A, B, true, 0.01, 1, 0.1, false);
        System.out.println();

        m = new Trapese();
        m.solve(Matrix.copy(xk), A, B, true, 0.01, 1, 0.1, false);
        System.out.println();

        m = new RungeKutta();
        m.solve(Matrix.copy(xk), A, B, true, 0.01, 1, 0.1, false);
        System.out.println();

        m = new PredictorCorector(new Euler(), new ReverseEuler(), 2);
        m.solve(Matrix.copy(xk), A, B, true, 0.01, 1, 0.1, false);
        System.out.println();

        m = new PredictorCorector(new Euler(), new Trapese(), 1);
        m.solve(Matrix.copy(xk), A, B, true, 0.01, 1, 0.1, false);
        System.out.println();

    }
}
