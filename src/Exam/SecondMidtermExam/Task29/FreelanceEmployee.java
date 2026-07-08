package Exam.SecondMidtermExam.Task29;

import java.util.List;

public class FreelanceEmployee extends AbstractEmployee {
    private List<Integer> ticketPoints;
    private int ticketPointsSum;


    public FreelanceEmployee(String name, String level, double rate, List<Integer> ticketPoints) {
        super(name, level, rate);
        this.ticketPoints = ticketPoints;
        calculateSalary();
    }

    private void calculateSalary() {
        this.ticketPointsSum = ticketPoints.stream().mapToInt(t -> t).sum();
        salary = ticketPointsSum * rate;
    }

    @Override
    public double getOvertime() {
        return -1;
    }

    @Override
    public int getTicketPoints() {
        return ticketPoints.size();
    }

    @Override
    public String toString() {
        return String.format("%s Tickets count: %d Tickets points: %d", super.toString(), ticketPoints.size(), ticketPointsSum);
    }
}