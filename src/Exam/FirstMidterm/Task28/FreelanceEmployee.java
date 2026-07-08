package Exam.FirstMidterm.Task28;

import java.util.List;

public class FreelanceEmployee extends Employee {
    private List<Integer> ticketPoints;
    private int ticketPointsSum;

    public FreelanceEmployee(String id, String level, double rate, List<Integer> ticketPoints) {
        super(id, level, rate);
        this.ticketPoints = ticketPoints;
        calculateSalary();
    }


    private void calculateSalary() {
        ticketPointsSum = ticketPoints
                .stream()
                .mapToInt(t -> t)
                .sum();
        salary = ticketPointsSum * rate;
    }

    @Override
    public String toString() {
        return String.format("%s Tickets count: %d Tickets points: %d", getCommonData(), ticketPoints.size(), ticketPointsSum);
    }
}
