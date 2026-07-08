package Exercises._05_Intro_to_Maps.PayRollSystem;

import java.util.ArrayList;
import java.util.List;

public class FreelanceEmployee extends Employee {
    private List<Integer> points;

    public FreelanceEmployee(String id, String level, double rate, List<Integer> points) {
        super(id, level, rate);
        this.points = points;
    }

    public FreelanceEmployee(String id, String level, double rate) {
        super(id, level, rate);
        this.points = new ArrayList<>();
    }

    @Override
    public double calculateSalary() {
        return points.stream().mapToInt(p -> p).sum() * getRate();
    }

}
