package Exercises._05_Intro_to_Maps.PayRollSystem;

public class HourlyEmployee extends Employee {
    private double hours;

    public HourlyEmployee(String id, String level, double rate, double hours) {
        super(id, level, rate);
        this.hours = hours;
    }


    @Override
    public double calculateSalary() {
        double overtime = Math.max(0, hours - 40);
        double normal = hours - overtime;
        return normal * getRate() + overtime * getRate() * 1.5;
    }
}
