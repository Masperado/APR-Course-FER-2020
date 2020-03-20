package hr.fer.zari.apr.hw03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class PredictorCorector implements Method {

    private Method predictor;
    private Method corrector;
    private int noOfCorr;

    public PredictorCorector(Method predictor, Method corrector, int noOfCorr) {
        this.predictor = predictor;
        this.corrector = corrector;
        this.noOfCorr = noOfCorr;
    }

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
//                Files.write(Paths.get("predCorr.csv"), (String.format(Locale.US, "%.2f", i) + ", " + xk.toStringCsv() + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
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

        Matrix m1 = predictor.nextIter(xk,A,B,timeDependent,T,currentT);

        for (int i=0;i<noOfCorr;i++){
            m1 = corrector.correct(xk,m1,A,B,timeDependent,T,currentT);
        }

        return m1;
    }


    @Override
    public Matrix correct(Matrix xk, Matrix xk1, Matrix A, Matrix B, boolean timeDependent, double T, double currentT) {
        return null;
    }

}
