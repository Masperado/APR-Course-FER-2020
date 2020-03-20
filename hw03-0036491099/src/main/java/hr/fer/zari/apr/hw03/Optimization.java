package hr.fer.zari.apr.hw03;

public interface Optimization {

    Solution solve(Solution start);

    Solution solve(double a, double b);

    void setPomak(double pomak);
}
