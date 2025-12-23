Implement a class `Airports` with the following methods:

* `public void addAirport(String name, String country, String code, int passengers)` – method for adding a new airport (name, country, code, and number of passengers it transports yearly)
* `public void addFlights(String from, String to, int time, int duration)` – method for adding flights (departure airport code, arrival airport code, departure time in minutes passed from 0:00, flight duration in minutes). From airport A to airport B there can be multiple flights.
* `public void showFlightsFromAirport(String code)` – method that displays all flights from the airport with code `code`. First, the name of the airport is printed (format as in the sample output), then all flights are printed (format as in the sample output), ordered first lexicographically by the destination airport code, and then the flights to that airport by departure time (a fully correct implementation is considered to be one without calling sort methods).
* `public void showDirectFlightsFromTo(String from, String to)` – method that displays all direct flights from the airport with code `from` to the airport with code `to`.
* `public void showDirectFlightsTo(String to)` – method that displays all direct flights to the airport with code `to`.

All flights should be sorted by departure time (a fully correct implementation is considered to be one without calling sort methods).
