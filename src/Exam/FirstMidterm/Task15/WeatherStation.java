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


    public void addMeasurment(float temp, float wind, float hum, float vis, Date date) {
        if (measurements.isEmpty()) {
            measurements.add(new Measurement(temp, wind, hum, vis, date));
        }

        if (differsBy2andHalfMins(date)) return;

        measurements.add(new Measurement(temp, wind, hum, vis, date));

        removeOlderThanXDays(date);
    }


    public boolean differsBy2andHalfMins(Date date) {
        return measurements.stream().anyMatch(m -> {
            long diffInMs = date.getTime() - m.getDate().getTime();
            double diffInS = diffInMs / (1000.0 * 60);
            if (diffInS<= 2.5) {
                return true;
            }
            return false;
        });
    }

    public void removeOlderThanXDays(Date date) {
        //The date x days before the new date
        Date before = new Date(date.getTime() - (long) days * 1440 * 1000 * 60);
        measurements.removeIf(m -> m.getDate().before(before) || m.getDate().equals(before));
    }

    public int total() {
        return measurements.size();
    }

    public void status(Date from, Date to) {

        List<Measurement> status = measurements.stream().filter(m -> {
                    return m.getDate().after(from) &&
                            m.getDate().before(to) ||
                            m.getDate().equals(from) ||
                            m.getDate().equals(to);
                })
                .sorted()
                .collect(Collectors.toList());

        if(status.isEmpty()){
            throw new RuntimeException();
        }
        double avgTemp = status.stream()
                .mapToDouble(Measurement::getTemp)
                .average()
                .orElse(0);

        status.forEach(System.out::println);
        System.out.printf("Average temperature: %.2f\n", avgTemp);

    }
}
