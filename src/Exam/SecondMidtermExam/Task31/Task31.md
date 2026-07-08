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

### Starter code
```java
import java.util.Scanner;

public class StaduimTest {
		public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		String[] sectorNames = new String[n];
		int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			sectorNames[i] = parts[0];
			sectorSizes[i] = Integer.parseInt(parts[1]);
		}
		Stadium stadium = new Stadium(name);
		stadium.createSectors(sectorNames, sectorSizes);
		n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			try {
				stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
						Integer.parseInt(parts[2]));
			} catch (SeatNotAllowedException e) {
				System.out.println("SeatNotAllowedException");
			} catch (SeatTakenException e) {
				System.out.println("SeatTakenException");
			}
		}
		stadium.showSectors();
	}
}
```

### Solution
```java
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

public class StaduimTest {
		public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		String[] sectorNames = new String[n];
		int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			sectorNames[i] = parts[0];
			sectorSizes[i] = Integer.parseInt(parts[1]);
		}
		Stadium stadium = new Stadium(name);
		stadium.createSectors(sectorNames, sectorSizes);
		n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; ++i) {
			String line = scanner.nextLine();
			String[] parts = line.split(";");
			try {
				stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
						Integer.parseInt(parts[2]));
			} catch (SeatNotAllowedException e) {
				System.out.println("SeatNotAllowedException");
			} catch (SeatTakenException e) {
				System.out.println("SeatTakenException");
			}
		}
		stadium.showSectors();
	}
}

class SeatTakenException extends RuntimeException{
    public SeatTakenException() {

    }
}

class SeatNotAllowedException extends RuntimeException{
    public SeatNotAllowedException() {

    }
}




class Stadium {
    private String name;
    private Map<String, Sector> sectors;


    public Stadium(String name) {
        this.name = name;
        this.sectors = new HashMap<>();
    }

    public void createSectors(String[] sectorNames, int[] sizes) {
        for (int i = 0; i < sectorNames.length; i++) {
            sectors.putIfAbsent(sectorNames[i], new Sector(sectorNames[i], sizes[i]));
        }
    }

    public void buyTicket(String sectorName, int seat, int type) {
        sectors.get(sectorName).buyTicket(seat, type);
    }

    public void showSectors() {
        sectors.values()
                .stream()
                .sorted(Comparator.comparingInt(Sector::freeSeats).reversed().thenComparing(Sector::getCode))
                .forEach(System.out::println);
    }
}

class Sector {
    private String code;
    private int seats;
    private Map<Integer, Boolean> taken;
    private int type;
    private int seatsTaken;
    private boolean flag = false;

    public Sector(String code, int seats) {
        this.code = code;
        this.seats = seats;
        taken = new HashMap<>();
        type = 0;
        seatsTaken = 0;
    }

    public void buyTicket(int seat, int type) {

        if(taken.containsKey(seat)){
            throw new SeatTakenException();
        }
        
        
        if (!flag && type != 0) {
            this.type = type;
            flag = true;
        }

        if (this.type != type && type != 0) {
            throw new SeatNotAllowedException();
        }

        taken.put(seat, true);
        seatsTaken++;
    }
    
    public int freeSeats(){
        return seats - seatsTaken;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        int freeSeats = freeSeats();
        double percentage = 100 - (freeSeats * 1.0 / seats) * 100.0;;
        return String.format("%s\t%d/%d\t%.1f%%", code,freeSeats , seats, percentage);
    }
}
```