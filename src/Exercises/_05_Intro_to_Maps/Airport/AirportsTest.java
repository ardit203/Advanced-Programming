package Exercises._05_Intro_to_Maps.Airport;

public class AirportsTest {
    static void main() {
        AirportCollection airportCollection = new AirportCollection();

        // Airports
        airportCollection.addAirport("Skopje International Airport", "North Macedonia", "SKP", 2500000);
        airportCollection.addAirport("Thessaloniki Airport", "Greece", "SKG", 6000000);
        airportCollection.addAirport("Vienna International Airport", "Austria", "VIE", 32000000);
        airportCollection.addAirport("Istanbul Airport", "Turkey", "IST", 64000000);
        airportCollection.addAirport("Zurich Airport", "Switzerland", "ZRH", 31000000);
        airportCollection.addAirport("Frankfurt Airport", "Germany", "FRA", 70000000);

// Flights (time = minutes from midnight, duration = minutes)

// SKP departures
        airportCollection.addFlight("SKP", "VIE", 570, 95);    // 09:30, 1h35
        airportCollection.addFlight("SKP", "IST", 705, 80);    // 11:45, 1h20
        airportCollection.addFlight("SKP", "FRA", 990, 130);   // 16:30, 2h10

// VIE connections
        airportCollection.addFlight("VIE", "SKP", 465, 95);    // 07:45, 1h35
        airportCollection.addFlight("VIE", "FRA", 615, 85);    // 10:15, 1h25
        airportCollection.addFlight("VIE", "ZRH", 750, 70);    // 12:30, 1h10

// IST connections
        airportCollection.addFlight("IST", "SKP", 495, 80);    // 08:15, 1h20
        airportCollection.addFlight("IST", "FRA", 840, 170);   // 14:00, 2h50

// FRA connections
        airportCollection.addFlight("FRA", "IST", 1145, 165);  // 19:05, 2h45
        airportCollection.addFlight("FRA", "ZRH", 900, 55);    // 15:00, 0h55

// ZRH connections
        airportCollection.addFlight("ZRH", "FRA", 540, 55);    // 09:00, 0h55

        airportCollection.showFlightsFromAirport("VIE");
    }
}
