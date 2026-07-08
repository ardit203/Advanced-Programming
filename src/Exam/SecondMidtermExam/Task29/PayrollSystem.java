package Exam.SecondMidtermExam.Task29;

import java.util.*;
import java.util.stream.Collectors;

public class PayrollSystem {
    private Map<String, Double> hourlyRateByLevel;
    private Map<String, Double> ticketRateByLevel;
    private List<Employee> employees;

    public PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        this.hourlyRateByLevel = hourlyRateByLevel;
        this.ticketRateByLevel = ticketRateByLevel;
        this.employees = new ArrayList<>();
    }

    public Employee createEmployee(String line) {
        Employee employee = EmployeeFactory.createEmployee(line, hourlyRateByLevel, ticketRateByLevel);
        employees.add(employee);
        return employee;
    }

    public Map<String, Double> getOvertimeSalaryForLevels() {
        Map<String, Double> grouped = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getLevel,
                        Collectors.summingDouble(Employee::getOvertime)
                ));

        List<String> groupWithOnlyOne = grouped.keySet()
                .stream()
                .filter(key -> grouped.get(key) == -1)
                .collect(Collectors.toList());

        groupWithOnlyOne.forEach(grouped::remove);

        return grouped;
    }

    public void printStatisticsForOvertimeSalary() {
        DoubleSummaryStatistics stats = employees.stream()
                .filter(e -> e.getOvertime() != -1)
                .mapToDouble(Employee::getOvertime)
                .summaryStatistics();

        System.out.printf("Statistics for overtime salary: Min: %.2f Average: %.2f Max: %.2f Sum: %.2f",
                stats.getMin(), stats.getAverage(), stats.getMax(), stats.getSum());
    }

    public Map<String, Integer> ticketsDoneByLevel() {
        return employees.stream()
                .filter(e -> e.getTicketPoints() != -1)
                .collect(Collectors.groupingBy(
                        Employee::getLevel,
                        Collectors.summingInt(Employee::getTicketPoints)
                ));
    }

    public List<Employee> getFirstNEmployeesByBonus(int n) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getBonus).reversed())
                .limit(n)
                .collect(Collectors.toList());

    }
}