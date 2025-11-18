package Exam.FirstMidterm.Task28;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeFactory {
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
