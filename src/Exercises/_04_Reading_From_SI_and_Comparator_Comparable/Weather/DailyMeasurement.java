package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DailyMeasurement {
    private int day;
    private List<Double> measurements;

    public DailyMeasurement(int day) {
        this.day = day;
        this.measurements = new ArrayList<>();
    }

    public int getDay() {
        return day;
    }


    public static DailyMeasurement create(String line) {
        String[] tokens = line.split("\\s++");
        int day = Integer.parseInt(tokens[0]);

        String split;
        if (tokens[1].contains("F")) {
            split = "F";
        } else {
            split = "C";
        }

        DailyMeasurement dailyMeasurement = new DailyMeasurement(day);

        IntStream.range(1, tokens.length).forEach(i -> {
            String[] temp = tokens[i].split(split);

            if (split.equals("F")) {
                dailyMeasurement.measurements.add(convertToC(Double.parseDouble(temp[0])));
            } else {
                dailyMeasurement.measurements.add(Double.parseDouble(temp[0]));
            }
        });

        return dailyMeasurement;
    }


    public static double convertToF(double temp) {
        return (temp * 9) / 5.0 + 32;
    }

    public static double convertToC(double temp) {
        return (temp - 32) * 5 / 9.0;
    }


    public static String getMeasurementStat(DailyMeasurement measurement, char scale) {
        List<Double> m = measurement.measurements.stream().toList();

        if (scale == 'F') {
            m = m.stream()
                    .map(DailyMeasurement::convertToF)
                    .toList();
        }

        int count = (int) m.stream().filter(Objects::nonNull).count();
        double max = m.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
        double min = m.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
        double avg = m.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        return String.format("%3d Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c"
                , measurement.day, count, min, scale, max, scale, avg, scale);
    }
}
