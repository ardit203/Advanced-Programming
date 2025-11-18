package Exam.FirstMidterm.Task28;

import java.util.List;

public class FreelanceEmployee extends Employee {
    private List<Integer> points;

    public FreelanceEmployee(String id, String level, double rate, List<Integer> points) {
        super(id, level, rate);
        this.points = points;
    }

    public List<Integer> getPoints() {
        return points;
    }

    @Override
    public double salary() {
        return points.stream().mapToInt(tp -> tp).sum() * getRate();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " Tickets count: %d Tickets points: %d",
                points.size(),
                points.stream().mapToInt(i -> i).sum()
        );
    }
}
