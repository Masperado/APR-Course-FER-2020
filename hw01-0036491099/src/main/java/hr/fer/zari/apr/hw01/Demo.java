package hr.fer.zari.apr.hw01;

import java.io.IOException;
import java.nio.file.Paths;

import static hr.fer.zari.apr.hw01.Matrix.solveLU;
import static hr.fer.zari.apr.hw01.Matrix.solveLUP;

public class Demo {

    public static void main(String[] args) throws IOException {

        zero();

        System.out.println();

        prvi();

        System.out.println();

        drugi();

        System.out.println();

        treci();

        System.out.println();

        cetvrti();

        System.out.println();

        peti();

        System.out.println();

        sesti();

        System.out.println();

        sedmi();

        System.out.println();

        osmi();

        System.out.println();

        deveti();

        System.out.println();

        deseti();


    }


    public static void zero() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/zero1.txt"));
        Matrix B = Matrix.fromFile(Paths.get("matrix/zero2.txt"));

        Matrix C = Matrix.transpose(A);

        C.addToThis(Matrix.mul(Matrix.mul(Matrix.mulWithScalar(A, 0.5), B), Matrix.sub(A, Matrix.mulWithScalar(B, 2))));

        double x = C.getElement(0, 0);

        C.setElement(x, 1, 1);

        System.out.println(C);

    }

    private static void prvi() throws IOException {
        Matrix A = Matrix.fromFile(Paths.get("matrix/zero1.txt"));

        Matrix C = Matrix.transpose(Matrix.transpose(A));

        System.out.println(A.equals(C) + " USPOREDBA TREBA BITI S EPSILONOM");
    }

    private static void drugi() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/drugi.txt"));
        Matrix Again = Matrix.fromFile(Paths.get("matrix/drugi.txt"));
        Matrix b = Matrix.fromFile(Paths.get("matrix/drugib.txt"));

        try {
            System.out.println(solveLU(A, b));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        System.out.println(solveLUP(Again, b));
    }

    private static void treci() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/treci.txt"));
        Matrix Again = Matrix.fromFile(Paths.get("matrix/treci.txt"));
        Matrix b = Matrix.fromFile(Paths.get("matrix/drugib.txt"));

        try {
            System.out.println(solveLU(A, b));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            System.out.println(solveLUP(Again, b));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

    }

    private static void cetvrti() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/cetvrti.txt"));
        Matrix Again = Matrix.fromFile(Paths.get("matrix/cetvrti.txt"));
        Matrix b = Matrix.fromFile(Paths.get("matrix/cetvrtib.txt"));

        try {
            System.out.println(solveLU(A, b));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            System.out.println(solveLUP(Again, b));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

    }

    private static void peti() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/peti.txt"));
        Matrix Again = Matrix.fromFile(Paths.get("matrix/peti.txt"));
        Matrix b = Matrix.fromFile(Paths.get("matrix/petib.txt"));

        try {
            System.out.println(solveLU(A, b));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            System.out.println(solveLUP(Again, b));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

    }


    private static void sesti() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/sesti.txt"));
        Matrix Again = Matrix.fromFile(Paths.get("matrix/sesti.txt"));
        Matrix b = Matrix.fromFile(Paths.get("matrix/sestib.txt"));

        try {
            System.out.println(solveLU(A, b));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        try {
            System.out.println(solveLUP(Again, b));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

    }

    private static void sedmi() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/sedmi.txt"));
        Matrix Again = Matrix.fromFile(Paths.get("matrix/sedmi.txt"));

        try {
            Matrix inverse = Matrix.inverse(A);
            System.out.println(A);
            System.out.println(Matrix.mul(Again, inverse));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

    }

    private static void osmi() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/osmi.txt"));
        Matrix Again = Matrix.fromFile(Paths.get("matrix/osmi.txt"));

        try {
            Matrix inverse = Matrix.inverse(A);
            System.out.println(A);
            System.out.println(Matrix.mul(Again, inverse));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

    }

    private static void deveti() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/deveti.txt"));

        try {
            System.out.println(Matrix.determinant(A));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

    }

    private static void deseti() throws IOException {

        Matrix A = Matrix.fromFile(Paths.get("matrix/deseti.txt"));

        try {
            System.out.println(Matrix.determinant(A));
        } catch (RuntimeException e) {
            System.out.println(e);
        }

    }


}
