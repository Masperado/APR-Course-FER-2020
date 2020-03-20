package hr.fer.zari.apr.hw03;


public interface GoalFunction {

    void calculate(Solution solution);

    void calculateGradient(Solution solution);

    void calculateHesse(Solution solution);

    double calculate(double x);

    int getNumberOfCalls();

    int getNumberOfCallsGrad();

    int getNumberOfCallsHesse();

}
