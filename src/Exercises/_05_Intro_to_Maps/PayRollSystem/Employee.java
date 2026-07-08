package Exercises._05_Intro_to_Maps.PayRollSystem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Employee implements Comparable<Employee> {
    private String id;
    private String level;
    private double rate;

    public Employee(String id, String level, double rate) {
        this.id = id;
        this.level = level;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public String getLevel() {
        return level;
    }

    public double getRate() {
        return rate;
    }

    public abstract double calculateSalary();

    @Override
    public int compareTo(Employee other) {
        return Comparator.comparing(Employee::calculateSalary).reversed().thenComparing(Employee::getLevel).compare(this, other);
    }


    public static Employee create(String line, Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        String[] tokens = line.split(";");
        String type = tokens[0];
        String id = tokens[1];
        String level = tokens[2];

        if (type.equals("H")) {
            double hours = Double.parseDouble(tokens[3]);
            double rate = hourlyRateByLevel.get(level);
            return new HourlyEmployee(id, level, rate, hours);
        } else if (type.equals("F")) {
            double rate = ticketRateByLevel.get(level);
            List<Integer> points = Arrays.stream(tokens).skip(3).map(Integer::parseInt).collect(Collectors.toList());
            return new FreelanceEmployee(id, level, rate, points);
        } else {
            throw new RuntimeException();
        }
    }
}
