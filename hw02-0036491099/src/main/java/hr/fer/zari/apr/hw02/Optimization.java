package hr.fer.zari.apr.hw02;

public interface Optimization {

    Solution solve(Solution start);

    Solution solve(double a, double b);

    void setPomak(double pomak);
}
