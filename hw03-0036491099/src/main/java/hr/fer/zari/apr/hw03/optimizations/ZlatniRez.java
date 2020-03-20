package hr.fer.zari.apr.hw03.optimizations;

import hr.fer.zari.apr.hw03.GoalFunction;
import hr.fer.zari.apr.hw03.Optimization;
import hr.fer.zari.apr.hw03.Solution;

public class ZlatniRez implements Optimization {

    private final double e = 10E-6;
    private final double k = 0.5 * (Math.sqrt(5) - 1);
    private final double h = 0.01;
    private final GoalFunction goalFunction;

    public ZlatniRez(GoalFunction goalFunction) {
        this.goalFunction = goalFunction;
    }

    @Override
    public Solution solve(Solution start) {

        double tocka = start.getX(0);

        double l = tocka - h;
        double r = tocka + h;

        double m = tocka;
        int step = 1;

        double fm = goalFunction.calculate(tocka);
        double fl = goalFunction.calculate(l);
        double fr = goalFunction.calculate(r);

        if (fm < fr && fm < fl) {
            return solve(l, r);
        } else if (fm > fr) {
            do {
                l = m;
                m = r;
                fm = fr;
                r = tocka + h * (step *= 2);
                fr = goalFunction.calculate(r);
            } while (fm > fr);
        } else {
            do {
                r = m;
                m = l;
                fm = fl;
                l = tocka - h * (step *= 2);
                fl = goalFunction.calculate(l);
            } while (fm > fl);
        }

        return solve(l, r);
    }

    @Override
    public Solution solve(double a, double b) {

        Solution sol = new Solution(a, b);

        double fa = goalFunction.calculate(a);
        double fb = goalFunction.calculate(b);

        double c = b - k * (b - a);
        double d = a + k * (b - a);
        double fc = goalFunction.calculate(c);
        double fd = goalFunction.calculate(d);

        while ((b - a) > e) {

            if (fc < fd) {
                b = d;
                fb = fd;
                d = c;
                c = b - k * (b - a);
                fd = fc;
                fc = goalFunction.calculate(c);
            } else {
                a = c;
                fa = fc;
                c = d;
                d = a + k * (b - a);
                fc = fd;
                fd = goalFunction.calculate(d);
            }
        }

        Solution ret = new Solution((a + b) / 2);
        goalFunction.calculate(ret);

        return ret;
    }

    @Override
    public void setPomak(double pomak) {

    }
}
