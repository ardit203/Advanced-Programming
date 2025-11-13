package Exercises._05_Intro_to_Maps.PayRollSystem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;
import java.util.function.Supplier;
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

    public void readEmployeesData(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        employees = br.lines().filter(Objects::nonNull)
                .map(l -> Employee.create(l, hourlyRateByLevel, ticketRateByLevel))
                .collect(Collectors.toList());
    }


    public TreeSet<Employee> grouped(String level) {
        return employees.stream()
                .filter(employee -> employee.getLevel().equals(level))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Map<String, Set<Employee>> printEmployeesByLevels(OutputStream os, Set<String> levels) {
//        Map<String, Set<Employee>> grouped = employees.stream().collect(Collectors.groupingBy(
//                Employee::getLevel,
//                (Supplier<TreeMap<String, Set<Employee>>>) TreeMap::new,
//                Collectors.toCollection(TreeSet::new)
//        ));

        Map<String, Set<Employee>> result = new TreeMap<>();

        for (String level : levels) {
            result.put(level, grouped(level));
        }

        return result;
    }

    public Map<String, Double> totalPayPerEmployee() {
        Map<String, Double> map = new HashMap<>();

        for (Employee employee : employees) {
            if (!map.containsKey(employee.getId())) {
                map.put(employee.getId(), employee.calculateSalary());
            } else {
                double currentVal = map.get(employee.getId());
                currentVal += employee.calculateSalary();
                map.put(employee.getId(), currentVal);
            }
        }
        return map;
    }
}
