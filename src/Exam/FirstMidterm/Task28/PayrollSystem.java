package Exam.FirstMidterm.Task28;


import java.io.*;
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

    public void readEmployees(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        employees = br.lines()
                .map(l -> EmployeeFactory.createEmployee(l, hourlyRateByLevel, ticketRateByLevel))
                .collect(Collectors.toList());
    }

    public Map<String, Set<Employee>> printEmployeesByLevels(PrintStream os, Set<String> levels) {
        Map<String, Set<Employee>> grouped = employees.stream()
                .filter(e -> levels.contains(e.getLevel()))
                .collect(Collectors.groupingBy(
                        Employee::getLevel,
                        TreeMap::new,
                        Collectors.toCollection(TreeSet::new)
                ));

        PrintWriter pw = new PrintWriter(os);

        grouped.forEach((key, value) -> {
            pw.println("LEVEL: " + key);
            pw.println("Employees: ");
            value.forEach(pw::println);
            pw.println("------------");
        });

        pw.flush();

        return grouped;
    }
}
