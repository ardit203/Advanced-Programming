package Exam.FirstMidterm.Task28;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PayrollSystem {
    private List<Employee> employees;
    private Map<String, Double> hourlyRateByLevel;
    private Map<String, Double> ticketRateByLevel;

    PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        this.hourlyRateByLevel = hourlyRateByLevel;
        this.ticketRateByLevel = ticketRateByLevel;
        this.employees = new ArrayList<>();
    }


    public void readEmployees(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        employees = br.lines()
                .map(l -> EmployeeFactory.create(l, hourlyRateByLevel, ticketRateByLevel))
                .collect(Collectors.toList());
    }


    public Map<String, Set<Employee>> printEmployeesByLevels (OutputStream os, Set<String> levels){
        Map<String, Set<Employee>> map = new TreeMap<>();

        Map<String, Set<Employee>> grouped = employees.stream().collect(Collectors.groupingBy(
                Employee::getLevel,
                (Supplier<TreeMap<String, Set<Employee>>>) TreeMap::new,
                Collectors.toCollection(TreeSet::new)
        ));

        for (String level : levels){
            Set<Employee> set = grouped.get(level);
            if(set != null){
                map.put(level, set);
            }
        }
        return map;
    }

    public Map<String, Double> totalPayPerEmployee(){
        return employees.stream().collect(Collectors.groupingBy(
                Employee::getId,
                TreeMap::new,
                Collectors.summingDouble(Employee::salary)
        ));

//        for (Employee e: employees){
//            totals.merge(e.getId(), e.salary(), Double::sum);
//        }
    }
}
