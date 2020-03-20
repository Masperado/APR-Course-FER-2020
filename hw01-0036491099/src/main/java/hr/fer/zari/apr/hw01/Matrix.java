package hr.fer.zari.apr.hw01;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Matrix {

    private static final double EPS = 1E-7;

    private int x;

    private int y;

    private double[][] data;

    public Matrix(int x, int y) {
        this.x = x;
        this.y = y;
        this.data = new double[x][y];
    }

    public Matrix(Dimension dimension) {
        this.x = dimension.width;
        this.y = dimension.height;
        this.data = new double[x][y];
    }

    public void setElement(double element, int x, int y) {
        data[x][y] = element;
    }

    public static Matrix fromFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);

        int y = lines.get(0).split(" ").length;
        Matrix m = new Matrix(lines.size(), y);

        for (int i = 0; i < lines.size(); i++) {
            String[] numbers = lines.get(i).split(" ");
            for (int j = 0; j < y; j++) {
                m.setElement(Double.parseDouble(numbers[j]), i, j);
            }
        }

        return m;
    }

    public void toFile(Path path) throws IOException {
        Files.write(path, toString().getBytes(StandardCharsets.UTF_8));
    }

    public double getElement(int x, int y) {
        return data[x][y];
    }

    public Dimension getDimension() {
        return new Dimension(x, y);
    }

    public void addToThis(Matrix other) {
        if (!getDimension().equals(other.getDimension())) {
            throw new RuntimeException("Dimensions are not equal!");
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                data[i][j] = data[i][j] + other.getElement(i, j);
            }
        }

    }

    public static Matrix add(Matrix first, Matrix other) {
        if (!first.getDimension().equals(other.getDimension())) {
            throw new RuntimeException("Dimensions are not equal!");
        }

        Matrix returnMatrix = new Matrix(first.getDimension().width, first.getDimension().height);

        for (int i = 0; i < first.getDimension().width; i++) {
            for (int j = 0; j < first.getDimension().height; j++) {
                returnMatrix.setElement(first.getElement(i, j) + other.getElement(i, j), i, j);
            }
        }

        return returnMatrix;

    }

    public void subToThis(Matrix other) {
        if (!getDimension().equals(other.getDimension())) {
            throw new RuntimeException("Dimensions are not equal!");
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                data[i][j] = data[i][j] - other.getElement(i, j);
            }
        }

    }

    public static Matrix sub(Matrix first, Matrix other) {
        if (!first.getDimension().equals(other.getDimension())) {
            throw new RuntimeException("Dimensions are not equal!");
        }

        Matrix returnMatrix = new Matrix(first.getDimension().width, first.getDimension().height);

        for (int i = 0; i < first.getDimension().width; i++) {
            for (int j = 0; j < first.getDimension().height; j++) {
                returnMatrix.setElement(first.getElement(i, j) - other.getElement(i, j), i, j);
            }
        }

        return returnMatrix;
    }

    public static Matrix mul(Matrix first, Matrix other) {
        if (first.getDimension().height != other.getDimension().width) {
            throw new RuntimeException("Height of first and width of second are not equal!");
        }

        Matrix returnMatrix = new Matrix(first.getDimension().width, other.getDimension().height);

        for (int i = 0; i < first.getDimension().width; i++) {
            for (int j = 0; j < other.getDimension().height; j++) {

                double temp = 0;
                for (int k = 0; k < first.getDimension().height; k++) {
                    temp += first.getElement(i, k) * other.getElement(k, j);
                }

                returnMatrix.setElement(temp, i, j);
            }
        }

        return returnMatrix;
    }

    public static Matrix transpose(Matrix m) {

        Matrix returnMatrix = new Matrix(m.getDimension().height, m.getDimension().width);

        for (int i = 0; i < m.getDimension().width; i++) {
            for (int j = 0; j < m.getDimension().height; j++) {
                returnMatrix.setElement(m.getElement(i, j), j, i);
            }
        }

        return returnMatrix;
    }

    public static Matrix mulWithScalar(Matrix m, double scalar) {

        Matrix returnMatrix = new Matrix(m.getDimension().width, m.getDimension().height);

        for (int i = 0; i < m.getDimension().width; i++) {
            for (int j = 0; j < m.getDimension().height; j++) {
                returnMatrix.setElement(m.getElement(i, j) * scalar, i, j);
            }
        }

        return returnMatrix;
    }

    public static Matrix forwardSupstitution(Matrix L, Matrix P, Matrix b) {
        Matrix y = new Matrix(b.getDimension());

        for (int i = 0; i < b.getDimension().width; i++) {
            double yTemp = b.getElement((int) P.getElement(i, 0), 0);
            for (int j = 0; j < i; j++) {
                yTemp -= L.getElement(i, j) * y.getElement(j, 0);
            }
            y.setElement(yTemp, i, 0);
        }

        return y;

    }

    public static Matrix backwardSupstitution(Matrix U, Matrix y) {
        Matrix x = new Matrix(y.getDimension());

        for (int i = y.getDimension().width - 1; i >= 0; i--) {
            double xTemp = 0;
            for (int j = y.getDimension().width - 1; j > i; j--) {
                xTemp += U.getElement(i, j) * x.getElement(j, 0);
            }

            if (Math.abs(U.getElement(i, i)) < EPS) {
                throw new RuntimeException("Matrix is singular!");
            }

            x.setElement((1 / U.getElement(i, i) * (y.getElement(i, 0) - xTemp)), i, 0);
        }

        return x;
    }

    public void LU() {
        if (getDimension().height != getDimension().width) {
            throw new RuntimeException("Matrix isn't quadratic");
        }

        for (int k = 0; k < getDimension().height; k++) {
            for (int i = k + 1; i < getDimension().height; i++) {

                if (Math.abs(getElement(k, k)) < EPS) {
                    throw new RuntimeException("Matrix cannot be solved with LU decomposition, please try LUP!");
                }

                setElement(getElement(i, k) / getElement(k, k), i, k);
                for (int j = k + 1; j < getDimension().height; j++) {
                    setElement(getElement(i, j) - getElement(i, k) * getElement(k, j), i, j);
                }
            }
        }
    }


    public void LUP(Matrix P) {
        if (getDimension().height != getDimension().width) {
            throw new RuntimeException("Matrix isn't quadratic");
        }

        for (int k = 0; k < getDimension().height; k++) {

            double pivot = 0;
            int l = 0;
            for (int i = k; i < getDimension().height; i++) {
                if (Math.abs(getElement(i, k)) > pivot) {
                    pivot = Math.abs(getElement(i, k));
                    l = i;
                }
            }
            if (Math.abs(pivot) < EPS) {
                throw new RuntimeException("Matrix is singular!");
            }
            if (l != k) {
                double temp = P.getElement(k, 0);
                P.setElement(P.getElement(l, 0), k, 0);
                P.setElement(temp, l, 0);
                P.setElement(P.getElement(getDimension().width, 0) + 1, getDimension().width, 0);

                for (int j = 0; j < getDimension().width; j++) {
                    temp = getElement(k, j);
                    setElement(getElement(l, j), k, j);
                    setElement(temp, l, j);
                }
            }

            for (int i = k + 1; i < getDimension().height; i++) {
                setElement(getElement(i, k) / getElement(k, k), i, k);
                for (int j = k + 1; j < getDimension().height; j++) {
                    setElement(getElement(i, j) - getElement(i, k) * getElement(k, j), i, j);
                }
            }
        }
    }

    public static Matrix solveLU(Matrix A, Matrix b) {
        A.LU();

        Matrix P = new Matrix(b.getDimension());
        for (int i = 0; i < b.getDimension().width; i++) {
            P.setElement(i, i, 0);
        }

        Matrix y = forwardSupstitution(A, P, b);
        Matrix x = backwardSupstitution(A, y);

        return x;

    }

    public static Matrix solveLUP(Matrix A, Matrix b) {

        Matrix P = new Matrix(b.getDimension().width + 1, 1);
        for (int i = 0; i < b.getDimension().width; i++) {
            P.setElement(i, i, 0);
        }

        A.LUP(P);

        Matrix y = forwardSupstitution(A, P, b);
        Matrix x = backwardSupstitution(A, y);

        return x;

    }

    public static Matrix inverse(Matrix A) {
        Matrix P = new Matrix(A.getDimension().width + 1, 1);
        for (int i = 0; i < A.getDimension().width; i++) {
            P.setElement(i, i, 0);
        }
        A.LUP(P);

        Matrix inv = new Matrix(A.getDimension());

        for (int i = 0; i < A.getDimension().width; i++) {
            Matrix e = new Matrix(A.getDimension().width, 1);
            e.setElement(1, i, 0);

            Matrix y = forwardSupstitution(A, P, e);
            Matrix x = backwardSupstitution(A, y);

            for (int j = 0; j < A.getDimension().width; j++) {
                inv.setElement(x.getElement(j, 0), j, i);
            }
        }

        return inv;
    }

    public static double determinant(Matrix A) {
        Matrix P = new Matrix(A.getDimension().width + 1, 1);
        for (int i = 0; i < A.getDimension().width; i++) {
            P.setElement(i, i, 0);
        }
        A.LUP(P);


        double determinant = Math.pow(-1, P.getElement(A.getDimension().width, 0));
        for (int i = 0; i < A.getDimension().width; i++) {
            determinant *= A.getElement(i, i);
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sb.append(String.format(Locale.US, "%.4f", data[i][j])).append(" ");
            }
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return x == matrix.x &&
                y == matrix.y &&
                matEquals(data, matrix.data, x, y);
    }

    private boolean matEquals(double[][] data, double[][] data1, int x, int y) {

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (Math.abs(data[i][j] - data1[i][j]) > EPS) {
                    return false;
                }
            }
        }

        return true;

    }

    @Override
    public int hashCode() {
        int result = Objects.hash(x, y);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }


}
