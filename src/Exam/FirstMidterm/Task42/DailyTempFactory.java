package Exam.FirstMidterm.Task42;

import java.util.ArrayList;
import java.util.List;

class DailyTempFactory {
    public static DailyMeasurement createDailyTemp(String line) {
        String[] tokens = line.split("\\s++");

        int day = Integer.parseInt(tokens[0]);
        List<String> tempParts = List.of(tokens[1].split(""));
        String type = tempParts.contains("C") ? "C" : "F";

        List<Double> temps = parse(tokens, type);

        return new DailyMeasurement(day, temps);
    }

    private static List<Double> parse(String[] tokens, String type) {
        List<Double> temps = new ArrayList<>();
        for (int i = 1; i < tokens.length; i++) {
            String temp = tokens[i].split(type)[0];
            temps.add(
                    type.equals("C") ? Double.parseDouble(temp) : convertToCelsius(temp)
            );
        }
        return temps;
    }

    private static double convertToCelsius(String temp) {
        double t = Double.parseDouble(temp);
        return (t - 32) * 5.0 / 9.0;
    }
}