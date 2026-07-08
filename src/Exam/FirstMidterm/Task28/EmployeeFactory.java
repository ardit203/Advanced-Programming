package Exam.FirstMidterm.Task28;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeFactory {
    public static Employee createEmployee(String line, Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel){
        String [] tokens = line.split(";");

        String type = tokens[0];
        String id = tokens[1];
        String level = tokens[2];

        if (type.equalsIgnoreCase("h")){
            double hours = Double.parseDouble(tokens[3]);
            return new HourlyEmployee(id, level, hourlyRateByLevel.get(level), hours);
        }else {
            List<Integer> ticketPoints = new ArrayList<>();
            for (int i = 3; i < tokens.length; i++) {
                ticketPoints.add(Integer.parseInt(tokens[i]));
            }
            return new FreelanceEmployee(id, level, ticketRateByLevel.get(level), ticketPoints);
        }
    }
}
