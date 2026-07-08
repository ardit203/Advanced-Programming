package Exam.FirstMidterm.Task42;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

class DailyMeasurement {
    private int day;
    private List<Double> temperatures;

    public DailyMeasurement(int day, List<Double> temperatures) {
        this.day = day;
        this.temperatures = temperatures;
    }

    public int getDay() {
        return day;
    }

    private List<Double> getTemperaturesInF() {
        return temperatures.stream()
                .map(t -> t * (9.0 / 5) + 32)
                .collect(Collectors.toList());
    }

    public String printStats(char type) {
        List<Double> temps =
                Character.toLowerCase(type) == Character.toLowerCase('F')
                        ? getTemperaturesInF()
                        : temperatures;

        DoubleSummaryStatistics stats = temps.stream()
                .mapToDouble(d -> d)
                .summaryStatistics();

        return String.format("%3d: Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c",
                day, temps.size(), stats.getMin(), type, stats.getMax(), type, stats.getAverage(), type);
    }

}