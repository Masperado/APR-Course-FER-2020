package hr.fer.zari.apr.hw03;

public interface Method {

    Matrix nextIter(Matrix xk, Matrix A, Matrix B, boolean timeDependent, double T, double currentT);

    Matrix correct(Matrix xk, Matrix xk1, Matrix A, Matrix B, boolean timeDependent, double T, double currentT);

    void solve(Matrix xk, Matrix A, Matrix B, boolean timeDependent, double T, double tMax, double tPrint, boolean firstTask);
}
