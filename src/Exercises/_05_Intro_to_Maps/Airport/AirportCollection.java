package Exercises._05_Intro_to_Maps.Airport;

import java.util.*;
import java.util.stream.Collectors;

public class AirportCollection {
    private Map<String, Airport> airportMap;
    private Map<String, Set<Flight>> flightsFrom;


    public AirportCollection() {
        airportMap = new TreeMap<>();
        flightsFrom = new TreeMap<>();
    }

    public void addAirport(String name, String country, String code, int passenger) {
        airportMap.put(code, new Airport(code, name, country, passenger));
    }

    public void addFlight(String from, String to, int time, int duration) {
        flightsFrom.computeIfAbsent(from, k -> new TreeSet<>()).add(new Flight(from, to, time, duration));
    }

    public void showFlightsFromAirport(String code) {
        Airport airport = airportMap.get(code);
        Set<Flight> flights = flightsFrom.get(code);

        flights.forEach(System.out::println);
    }

    public void showDirectFlightsFromTo(String from, String to) {
        Set<Flight> flights = flightsFrom.get(from);

        System.out.printf("Flights from %s to %s\n", from, to);

        Set<Flight> set = new TreeSet<>();

        for (Flight flight : flights) {
            if (flight.getTo().equals(to)) {
                set.add(flight);
            }
        }

        set.forEach(System.out::println);
    }

    public void showDirectFlightsTo(String to) {
        Set<Flight> flights = new TreeSet<>();
        for (Set<Flight> set : flightsFrom.values()) {
            for (Flight value : set) {
                if(value.getTo().equals(to)){
                    flights.add(value);
                }
            }
        }
        System.out.printf("Flights to %s\n", to);
        flights.forEach(System.out::println);
    }
}
