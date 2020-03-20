package hr.fer.zari.apr.hw03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Solution {

    private List<Double> inputs;

    private Matrix gradient;

    private Matrix hesse;

    private double output;

    public Solution(double... inputs) {
        this.inputs = DoubleStream.of(inputs).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    public Solution(List<Double> inputs) {
        this.inputs = inputs;
    }

    public Solution(Matrix m) {
        inputs = new ArrayList<>();

        for (int i = 0; i < m.getDimension().width; i++) {
            inputs.add(m.getElement(i, 0));
        }

    }

    public List<Double> getInputs() {
        return inputs;
    }

    public double getX(int i) {
        return inputs.get(i);
    }

    public void setInputs(List<Double> inputs) {
        this.inputs = inputs;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public Matrix getGradient() {
        return gradient;
    }

    public void setGradient(Matrix gradient) {
        this.gradient = gradient;
    }

    public Matrix getHesse() {
        return hesse;
    }

    public void setHesse(Matrix hesse) {
        this.hesse = hesse;
    }

    public Solution copy() {
        return new Solution(new ArrayList<>(inputs));
    }

    public double distance(Solution other) {
        double distance = 0;

        for (int i = 0; i < inputs.size(); i++) {
            distance += Math.pow(inputs.get(i) - other.getX(i), 2);
        }

        return Math.sqrt(distance);
    }

    @Override
    public String toString() {
        return inputs.toString();
    }
}
