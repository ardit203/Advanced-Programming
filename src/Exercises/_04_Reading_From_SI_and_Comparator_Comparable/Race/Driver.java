package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Race;

import java.util.ArrayList;
import java.util.List;

public class Driver implements Comparable<Driver> {
    private String name;
    private List<String> laps;

    public Driver(String name) {
        this.name = name;
        this.laps = new ArrayList<>();
    }

    public static Driver create(String line) {
        String[] tokens = line.split("\\s++");
        Driver driver = new Driver(tokens[0]);
        driver.laps.add(tokens[1]);
        driver.laps.add(tokens[2]);
        driver.laps.add(tokens[3]);
        return driver;
    }

    public static int getLapMillis(String lap) {
        String[] time = lap.split(":");
        return Integer.parseInt(time[0]) * 60 * 1000 + Integer.parseInt(time[1]) * 1000 + Integer.parseInt(time[2]);
    }

    public String getBestLap() {
        int min = laps.stream().mapToInt(Driver::getLapMillis).min().orElse(0);
        return laps.stream().filter(l -> getLapMillis(l) == min).findFirst().orElseGet(() -> laps.getLast());
    }


    @Override
    public String toString() {
        return String.format("%-10s%10s", name, getBestLap());
    }

    @Override
    public int compareTo(Driver other) {
        return Integer.compare(getLapMillis(getBestLap()), getLapMillis(other.getBestLap()));
    }
}
