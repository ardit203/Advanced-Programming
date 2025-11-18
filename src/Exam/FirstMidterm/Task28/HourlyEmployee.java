package Exam.FirstMidterm.Task28;

public class HourlyEmployee extends Employee {
    private double hours;
    private double overtime;
    private double regular;

    public HourlyEmployee(String id, String level, double rate, double hours) {
        super(id, level, rate);
        this.hours = hours;
        this.overtime = Math.max(0, hours - 40);
        this.regular = hours - overtime;
    }

    public double getHours() {
        return hours;
    }

    @Override
    public double salary() {
        return regular * getRate() + overtime * getRate() * 1.5;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" Regular hours: %.2f Overtime hours: %.2f", regular, overtime);
    }
}
