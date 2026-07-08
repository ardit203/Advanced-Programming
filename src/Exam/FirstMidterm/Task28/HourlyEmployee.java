package Exam.FirstMidterm.Task28;

public class HourlyEmployee extends Employee {
    private double hours;
    private double regularHours;
    private double overtime;


    public HourlyEmployee(String id, String level, double rate, double hours) {
        super(id, level, rate);
        this.hours = hours;
        calculateSalary();
    }


    protected void calculateSalary() {
        regularHours = Math.min(hours, 40);
        overtime = Math.max(0, hours - 40);

        salary = regularHours * rate + overtime * rate * 1.5;
    }

    @Override
    public String toString() {
        return String.format("%s Regular hours: %.2f Overtime hours: %.2f", getCommonData(), regularHours, overtime);
    }
}
