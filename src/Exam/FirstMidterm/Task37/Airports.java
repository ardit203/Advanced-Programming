package Exam.FirstMidterm.Task37;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class Airports {
    private Map<String, Airport> airports;
    private Map<String, Set<Flight>> from;
    private Map<String, Set<Flight>> to;

    public Airports() {
        this.airports = new HashMap<>();
        this.from = new HashMap<>();
        this.to = new HashMap<>();
    }


    public void addAirport(String name, String country, String code, int passengers) {
        airports.put(code, new Airport(name, country, code, passengers));
    }

    public void addFlights(String from, String to, int time, int duration) {
        Flight flight = new Flight(from, to, time, duration);
        this.from.computeIfAbsent(from, k -> new TreeSet<>()).add(flight);
        this.to.computeIfAbsent(to, k -> new TreeSet<>()).add(flight);
    }

    public void showFlightsFromAirport(String code) {
        Airport airport = airports.get(code);
        Set<Flight> flights = from.get(airport.getCode());
        System.out.println(airport);
        int index = 1;
        for (Flight flight : flights) {
            System.out.printf("%d. %s\n", index, flight);
            index++;
        }
    }

    public void showDirectFlightsFromTo(String from, String to) {
        Set<Flight> flights = this.from.get(from).stream()
                .filter(f -> f.getTo().equalsIgnoreCase(to))
                .collect(Collectors.toCollection(TreeSet::new));
        if (flights.isEmpty()) {
            System.out.printf("No flights from %s to %s\n", from, to);
            return;
        }
        flights.forEach(System.out::println);
    }

    public void showDirectFlightsTo(String to) {
        this.to.get(to)
                .forEach(System.out::println);
    }
}