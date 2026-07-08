Implement a class `Airports` with the following methods:

* `public void addAirport(String name, String country, String code, int passengers)` – method for adding a new airport (name, country, code, and number of passengers it transports yearly)
* `public void addFlights(String from, String to, int time, int duration)` – method for adding flights (departure airport code, arrival airport code, departure time in minutes passed from 0:00, flight duration in minutes). From airport A to airport B there can be multiple flights.
* `public void showFlightsFromAirport(String code)` – method that displays all flights from the airport with code `code`. First, the name of the airport is printed (format as in the sample output), then all flights are printed (format as in the sample output), ordered first lexicographically by the destination airport code, and then the flights to that airport by departure time (a fully correct implementation is considered to be one without calling sort methods).
* `public void showDirectFlightsFromTo(String from, String to)` – method that displays all direct flights from the airport with code `from` to the airport with code `to`.
* `public void showDirectFlightsTo(String to)` – method that displays all direct flights to the airport with code `to`.

All flights should be sorted by departure time (a fully correct implementation is considered to be one without calling sort methods).

### Starter code
```java
import java.util.*;

public class AirportsTest {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Airports airports = new Airports();
    int n = scanner.nextInt();
    scanner.nextLine();
    String[] codes = new String[n];
    for (int i = 0; i < n; ++i) {
      String al = scanner.nextLine();
      String[] parts = al.split(";");
      airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
      codes[i] = parts[2];
    }
    int nn = scanner.nextInt();
    scanner.nextLine();
    for (int i = 0; i < nn; ++i) {
      String fl = scanner.nextLine();
      String[] parts = fl.split(";");
      airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
    }
    int f = scanner.nextInt();
    int t = scanner.nextInt();
    String from = codes[f];
    String to = codes[t];
    System.out.printf("===== FLIGHTS FROM %S =====\n", from);
    airports.showFlightsFromAirport(from);
    System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
    airports.showDirectFlightsFromTo(from, to);
    t += 5;
    t = t % n;
    to = codes[t];
    System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
    airports.showDirectFlightsTo(to);
  }
}
```

### Solution
```java
import java.util.*;
import java.util.stream.Collectors;

class Airport {
    private String name;
    private String country;
    private String code;
    private int passengers;

    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)\n%s\n%d", name, code, country, passengers);
    }
}

class Flight implements Comparable<Flight> {
    private String from;
    private String to;
    private int time;
    private int duration;
    private String departureString;
    private String durationString;
    private String arrivalString;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
        compute();
    }

    private void compute() {
        departureString = String.format("%02d:%02d", time / 60, time % 60);

        int[] arrival = dur(time + duration);

        int days = arrival[0];
        int hours = arrival[1];
        int minutes = arrival[2];

        arrivalString = String.format("%02d:%02d", hours, minutes);

        int[] duration = dur(this.duration);
        int d = days + duration[0];
        int h = duration[1];
        int m = duration[2];

        String dStr = d == 0 ? "" : "+" + d + "d ";

        durationString = String.format("%s%dh%02dm", dStr, h, m);

    }

    private int[] dur(int time) {
        int d = time / (24 * 60);
        time %= (24 * 60);
        int h = time / 60;
        time %= 60;
        int m = time;

        return new int[]{d, h, m};
    }

    public String getTo() {
        return to;
    }

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return String.format("%s-%s %s-%s %s", from, to, departureString, arrivalString, durationString);
    }

    @Override
    public int compareTo(Flight other) {
        return Comparator.comparing(Flight::getTo).thenComparing(Flight::getTime).thenComparing(Flight::getDuration).compare(this, other);
    }
}

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

public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}
```