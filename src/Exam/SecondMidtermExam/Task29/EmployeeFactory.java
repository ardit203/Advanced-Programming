package Exam.SecondMidtermExam.Task29;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeFactory {
    public static Employee createEmployee(String line, Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        String[] parts = line.split("\\s++");

        Employee employee = createAbstractEmployee(parts[0], hourlyRateByLevel, ticketRateByLevel);

        if (parts.length != 1) {
            String bonusType = parts[1].contains("%") ? "percentage" : "fixed";
            double bonus = Double.parseDouble(parts[1].split("%")[0]);

            if (bonusType.equals("percentage")) {
                if (bonus > 20) {
                    throw new BonusNotAllowedException(String.format("%.2f%%", bonus));
                }
                employee = new PercentageBonus(employee, bonus);
            } else {
                if (bonus > 1000) {
                    throw new BonusNotAllowedException(String.format("%.0f$", bonus));
                }
                employee = new FixedBonus(employee, bonus);
            }
        }
        return employee;
    }

    public static Employee createAbstractEmployee(String line, Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        String[] tokens = line.split(";");
        String type = tokens[0];
        String name = tokens[1];
        String level = tokens[2];
        double rate;

        if (type.equalsIgnoreCase("h")) {
            rate = hourlyRateByLevel.get(level);
            double hours = Double.parseDouble(tokens[3]);
            return new HourlyEmployee(name, level, rate, hours);
        } else {
            rate = ticketRateByLevel.get(level);
            List<Integer> ticketPoints = new ArrayList<>();

            for (int i = 3; i < tokens.length; i++) {
                ticketPoints.add(Integer.parseInt(tokens[i]));
            }
            return new FreelanceEmployee(name, level, rate, ticketPoints);
        }
    }
}