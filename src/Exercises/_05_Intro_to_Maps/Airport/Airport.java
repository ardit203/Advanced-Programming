package Exercises._05_Intro_to_Maps.Airport;

import java.util.Comparator;

public class Airport implements Comparable<Airport> {
    private String code;
    private String name;
    private String country;
    private int passengers;

    public Airport(String code, String name, String country, int passengers) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.passengers = passengers;
    }

    @Override
    public int compareTo(Airport other) {
        return code.compareTo(other.code);
    }

    @Override
    public String toString() {
        return String.format("%s (%s)\n%s\n%d", name, code, country, passengers);
    }
}
