package Exam.FirstMidterm.Task15;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherStation {
    private int days;
    private List<Measurement> measurements;

    public WeatherStation(int days) {
        this.days = days;
        this.measurements = new ArrayList<>();
    }

    public void addMeasurement(float temperature, float wind, float humidity, float visibility, Date date) {
        if (measurements.isEmpty()) {
            measurements.add(new Measurement(temperature, wind, humidity, visibility, date));
            return;
        }

        int count = (int) measurements.stream()
                .filter(m -> {
                    long time1 = date.getTime();
                    long time2 = m.getDate().getTime();
                    long diffInMs = Math.abs(time1 - time2);
                    double diff = diffInMs / (1000.0 * 60);
                    if (diff <= 2.5) {
                        return true;
                    }
                    return false;
                }).count();

        if (count > 0) {
            return;
        }
        Date before = new Date(date.getTime() - (long) days * 1440 * 1000 * 60);

        measurements.removeIf(d -> d.getDate().before(before) || d.getDate().equals(date));


        measurements.add(new Measurement(temperature, wind, humidity, visibility, date));
    }


    public int total() {
        return measurements.size();
    }

    public void status(Date from, Date to) {
        List<Measurement> toBePrinted = measurements.stream()
                .filter(m -> {
                    if (m.getDate().equals(from) || m.getDate().equals(to)) {
                        return true;
                    }
                    return m.getDate().after(from) && m.getDate().before(to);
                })
                .sorted()
                .collect(Collectors.toList());

        if (toBePrinted.isEmpty()) {
            throw new RuntimeException();
        }

        double average = toBePrinted.stream().mapToDouble(Measurement::getTemp).average().orElse(0);

        toBePrinted.forEach(System.out::println);
        System.out.printf("Average temperature: %.2f", average);
    }
}
