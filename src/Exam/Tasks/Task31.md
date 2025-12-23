Implement a ticket system for a stadium. For that purpose, the following classes need to be implemented:

1. `Sector` in which information is stored about:

    * the sector code `String`
    * the number of seats `int`
    * information about the occupancy of the seats `?`
2. `Stadium` in which information is stored about:

    * the name of the stadium `String`
    * and all sectors in the stadium `?`

In the class `Stadium`, the following methods need to be implemented:

* `Stadium(String name)` – constructor with an argument for the stadium name
* `void createSectors(String[] sectorNames, int[] sizes)` – creates sectors with names `String[] sectorNames` and number of seats `int[] sizes` (both arrays are the same length)
* `void buyTicket(String sectorName, int seat, int type)` – for buying a ticket of the given type (`type`, 0 – neutral, 1 – home, 2 – away), in the sector `sectorName` with seat number `seat` (the seat is always within the range `1 – size`).
  If the seat is occupied (a ticket has already been bought for this seat), an exception of type `SeatTakenException` is thrown.
  Also, if we try to buy a type 1 ticket in a sector where a type 2 ticket has already been bought (and vice versa), an exception of type `SeatNotAllowedException` is thrown.
* `void showSectors()` – prints all sectors sorted by the number of free seats in descending order (if several sectors have the same number of free seats, they are sorted by name).