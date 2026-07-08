package Exam.SecondMidtermExam.Task29;

public class HourlyEmployee extends AbstractEmployee {
    private double hours;
    private double overtime;
    private double regularHours;

    public HourlyEmployee(String name, String level, double rate, double hours) {
        super(name, level, rate);
        this.hours = hours;
        calculateSalary();
    }

    private void calculateSalary() {
        this.regularHours = Math.min(hours, 40);
        this.overtime = Math.max(0, hours - 40);
        salary = regularHours * rate + overtime * rate * 1.5;
    }

    @Override
    public double getOvertime() {
        return overtime * rate * 1.5;
    }

    @Override
    public int getTicketPoints() {
        return -1;
    }

    @Override
    public String toString() {
        return String.format("%s Regular hours: %.2f Overtime hours: %.2f", super.toString(), regularHours, overtime);
    }
}