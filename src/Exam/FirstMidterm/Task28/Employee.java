package Exam.FirstMidterm.Task28;

import java.util.Comparator;

public abstract class Employee implements Comparable<Employee> {
    private String id;
    private double rate;
    private String level;

    public Employee(String id, String level, double rate) {
        this.id = id;
        this.level = level;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public double getRate() {
        return rate;
    }

    public String getLevel() {
        return level;
    }

    public abstract double salary();

    @Override
    public int compareTo(Employee other){
        return Comparator.comparingDouble(Employee::salary).compare(other, this);
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f", id, level, salary());
    }
}
