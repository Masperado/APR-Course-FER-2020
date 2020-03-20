package hr.fer.zari.apr.hw03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class Trapese implements Method {


    @Override
    public void solve(Matrix xk, Matrix A, Matrix B, boolean timeDependent, double T, double tMax, double tPrint, boolean firstTask) {

        StringBuilder sb = new StringBuilder();

        double temp = 0;

        double sum = 0;

        for (double i = 0; i < tMax; i += T) {
            xk = nextIter(xk, A, B, timeDependent, T, i);

            if (firstTask){
                sum+=Math.abs(xk.getElement(0,0)-Math.cos(i)-Math.sin(i));
                sum+=Math.abs(xk.getElement(1,0)-Math.cos(i)+Math.sin(i));
            }

//            try {
//                Files.write(Paths.get("trapese.csv"), (String.format(Locale.US, "%.2f", i) + ", " + xk.toStringCsv() + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            temp += T;
            if (temp >= tPrint) {
                System.out.println(xk.toStringCsv());
                temp = 0;
            }
        }

        if (firstTask){
            System.out.println(sum);
        }

    }


    @Override
    public Matrix nextIter(Matrix xk, Matrix A, Matrix B, boolean timeDependent, double T, double currentT) {

        Matrix U = new Matrix(A.getDimension().width, A.getDimension().height);
        for (int i = 0; i < U.getDimension().height; i++) {
            U.setElement(1, i, i);
        }

        Matrix temp1 = Matrix.inverse(Matrix.sub(U,Matrix.mulWithScalar(A,T/2)));
        Matrix temp2 = Matrix.add(U,Matrix.mulWithScalar(A,T/2));

        Matrix temp3 = Matrix.mul(Matrix.mul(temp1,temp2),xk);

        Matrix temp4= Matrix.mul(Matrix.mulWithScalar(temp1,T/2),B);

        if (timeDependent) {
            Matrix m = new Matrix(xk.getDimension().width, 1);
            for (int i = 0; i < m.getDimension().width; i++) {
                m.setElement(2*currentT+T, i, 0);
            }
            temp4 = Matrix.mul(temp4, m);
        } else {
            Matrix m = new Matrix(xk.getDimension().width, 1);
            for (int i = 0; i < m.getDimension().width; i++) {
                m.setElement(1, i, 0);
            }
            temp4 = Matrix.mul(temp4, m);
        }

        return Matrix.add(temp3, temp4);
    }


    @Override
    public Matrix correct(Matrix xk, Matrix xk1, Matrix A, Matrix B, boolean timeDependent, double T, double currentT) {

        Matrix temp1 = Matrix.mul(A, xk);
        Matrix temp2 = Matrix.copy(B);

        if (timeDependent) {
            Matrix m = new Matrix(xk.getDimension().width, 1);
            for (int i = 0; i < m.getDimension().width; i++) {
                m.setElement(currentT, i, 0);
            }
            temp2 = Matrix.mul(temp2, m);
        } else {
            Matrix m = new Matrix(xk.getDimension().width, 1);
            for (int i = 0; i < m.getDimension().width; i++) {
                m.setElement(1, i, 0);
            }
            temp2 = Matrix.mul(temp2, m);
        }

        Matrix temp3 = Matrix.add(temp1, temp2);

        Matrix temp4 = Matrix.mul(A, xk1);
        Matrix temp5 = Matrix.copy(B);

        if (timeDependent) {
            Matrix m = new Matrix(xk.getDimension().width, 1);
            for (int i = 0; i < m.getDimension().width; i++) {
                m.setElement(currentT, i, 0);
            }
            temp5 = Matrix.mul(temp5, m);
        } else {
            Matrix m = new Matrix(xk.getDimension().width, 1);
            for (int i = 0; i < m.getDimension().width; i++) {
                m.setElement(1, i, 0);
            }
            temp5 = Matrix.mul(temp5, m);
        }

        Matrix temp6 = Matrix.add(temp4, temp5);

        return Matrix.add(xk, Matrix.mulWithScalar(Matrix.add(temp3,temp6),T/2));
    }



}
