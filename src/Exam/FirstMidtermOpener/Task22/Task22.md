You need to implement a class `F1Race` which will read from an input stream (standard input, file, …) data about the lap times of the last 3 laps of several drivers in an F1 race.
The data is in the following format:

`Driver_name lap1 lap2 lap3`,
where each `lap` is in the format `mm:ss:nnn`, where `mm` is minutes, `ss` is seconds, and `nnn` is milliseconds.
Example:

```
Vettel 1:55:523 1:54:987 1:56:134
```

Your task is to implement the following methods:

* `F1Race()` – default constructor
* `void readResults(InputStream inputStream)` – method for reading the data
* `void printSorted(OutputStream outputStream)` – method which prints all drivers sorted by their best lap time (the shortest time from their 3 last laps) in the format
  `Driver_name best_lap`
  with 10 characters reserved for the driver's name (left aligned) and 10 characters for the best lap time (right aligned).
  The time must be printed in the same format as the input times.

### Starter code
```java
public class F1Test {

	public static void main(String[] args) {
		F1Race f1Race = new F1Race();
		f1Race.readResults(System.in);
		f1Race.printSorted(System.out);
	}

}

class F1Race {
	// TODO
    
}
```

### Solution
```java
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class F1Test {

	public static void main(String[] args) {
		F1Race f1Race = new F1Race();
		f1Race.readResults(System.in);
		f1Race.printSorted(System.out);
	}

}

class F1Race {
    private List<Driver> drivers;

    public F1Race() {
        this.drivers = new ArrayList<>();
    }

    public void readResults(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        drivers = br.lines()
                .map(Driver::createDriver)
                .collect(Collectors.toList());
    }

    public void printSorted(PrintStream os) {
        drivers = drivers.stream()
                .sorted()
                .collect(Collectors.toList());

        PrintWriter pw = new PrintWriter(os);

        for (int i = 0; i < drivers.size(); i++) {
            pw.println(String.format("%d. %s", i + 1, drivers.get(i)));
        }
        pw.flush();
    }
}


class Driver implements Comparable<Driver> {
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


class Lap implements Comparable<Lap> {
    private int minutes;
    private int seconds;
    private int milliseconds;

    public Lap(int minutes, int seconds, int milliseconds) {
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public long getTimeInMs(){
        long minsInMs = minutes * 60 * 1000L;
        long secsInMs = seconds * 1000L;

        return minsInMs + secsInMs + milliseconds;
    }

    public static Lap createLap(String line) {
        String[] tokens = line.split(":");
        int min = Integer.parseInt(tokens[0]);
        int sec = Integer.parseInt(tokens[1]);
        int ms = Integer.parseInt(tokens[2]);
        return new Lap(min, sec, ms);
    }

    @Override
    public int compareTo(Lap other) {
        return Long.compare(this.getTimeInMs(), other.getTimeInMs());
    }

    @Override
    public String toString() {
        return String.format("%d:%02d:%03d",minutes,seconds,milliseconds);
    }
}
```