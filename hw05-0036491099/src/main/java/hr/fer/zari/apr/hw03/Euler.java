package hr.fer.zari.apr.hw03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class Euler implements Method {


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
//                Files.write(Paths.get("euler.csv"), (String.format(Locale.US, "%.2f", i) + ", " + xk.toStringCsv() + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
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

        Matrix temp3 = Matrix.mulWithScalar(Matrix.add(temp1, temp2), T);

        return Matrix.add(xk, temp3);
    }

    @Override
    public Matrix correct(Matrix xk, Matrix xk1, Matrix A, Matrix B, boolean timeDependent, double T, double currentT) {
        return null;
    }
}
