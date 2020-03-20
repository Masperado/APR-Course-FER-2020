package hr.fer.zari.apr.hw02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Solution {

    private List<Double> inputs;

    private double output;

    public Solution(double ...inputs) {
        this.inputs = DoubleStream.of(inputs).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    public Solution(List<Double> inputs) {
        this.inputs = inputs;
    }

    public List<Double> getInputs() {
        return inputs;
    }

    public double getX(int i){
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

    public Solution copy(){
        return new Solution(new ArrayList<>(inputs));
    }

    public double distance(Solution other){
        double distance = 0;

        for (int i=0;i<inputs.size();i++){
            distance+= Math.pow(inputs.get(i)-other.getX(i),2);
        }

        return Math.sqrt(distance);
    }

    @Override
    public String toString() {
        return inputs.toString();
    }
}
