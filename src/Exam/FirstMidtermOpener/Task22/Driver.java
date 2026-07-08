package Exam.FirstMidtermOpener.Task22;

import java.util.*;

public class Driver implements Comparable<Driver> {
    private String name;
    private TreeSet<Lap> laps;

    public Driver(String name, TreeSet<Lap> laps) {
        this.name = name;
        this.laps = laps;
    }

    public static Driver createDriver(String line){
        String [] tokens = line.split("\\s++");

        String name = tokens[0];

        TreeSet<Lap> lapsList = new TreeSet<>();
        lapsList.add(Lap.createLap(tokens[1]));
        lapsList.add(Lap.createLap(tokens[2]));
        lapsList.add(Lap.createLap(tokens[3]));

        return new Driver(name, lapsList);
    }

    public Lap bestLap(){
        return laps.first();
    }

    @Override
    public int compareTo(Driver other) {
        return this.bestLap().compareTo(other.bestLap());
    }

    @Override
    public String toString() {
        return String.format("%-10s%10s", name, bestLap());
    }
}
