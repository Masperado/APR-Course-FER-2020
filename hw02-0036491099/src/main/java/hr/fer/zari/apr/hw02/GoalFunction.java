package hr.fer.zari.apr.hw02;


public interface GoalFunction {

    void calculate(Solution solution);

    double calculate(double x);

    int getNumberofCalls();
}
