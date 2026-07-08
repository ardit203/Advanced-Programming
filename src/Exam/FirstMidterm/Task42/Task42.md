Implement a class `DailyTemperatures` in which air temperatures (integers) are read for different days of the year (number from 1 to 366).
The temperatures for one day are in one line in the following format (example): `137 23C 15C 28C`.
The first number represents the day of the year, and then follows an unknown number of temperature measurements for that day, in either Celsius scale (C) or Fahrenheit scale (F).

In this class, implement the following methods:

* `DailyTemperatures()` – default constructor
* `void readTemperatures(InputStream inputStream)` – method for reading the data from an input stream
* `void writeDailyStats(OutputStream outputStream, char scale)` – method for printing the daily statistics (total measurements, minimum temperature, maximum temperature, average temperature) for each day, ordered in ascending order by the day.
  The second argument `scale` determines in which scale the temperatures are printed: `C - Celsius`, `F - Fahrenheit`.
  The format for printing the statistics for a given day is:

  `[day]: Count: [total measurements - 3 places] Min: [min temperature] Max: [max temperature] Avg: [average]`

The minimum, maximum, and average temperature are printed with 6 places, of which 2 are decimal, and after the number the scale of the temperature is written (C/F).

Formula for conversion from Celsius to Fahrenheit: $\frac{T \cdot 9}{5} + 32$


Formula for conversion from Fahrenheit to Celsius: $\frac{(T - 32) \cdot 5}{9}$


**Note:** To achieve the same precision as in the results of the solution, for calculating the average and converting between scales, the temperatures should be stored as type `Double`.

### Starter code
```java
/**
 * I partial exam 2016
 */
public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        dailyTemperatures.readTemperatures(System.in);
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
    }
}
```

### Solution
```java
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class DailyMeasurement {
    private int day;
    private List<Double> temperatures;

    public DailyMeasurement(int day, List<Double> temperatures) {
        this.day = day;
        this.temperatures = temperatures;
    }

    public int getDay() {
        return day;
    }

    private List<Double> getTemperaturesInF() {
        return temperatures.stream()
                .map(t -> t * (9.0 / 5) + 32)
                .collect(Collectors.toList());
    }

    public String printStats(char type) {
        List<Double> temps =
                Character.toLowerCase(type) == Character.toLowerCase('F')
                        ? getTemperaturesInF()
                        : temperatures;

        DoubleSummaryStatistics stats = temps.stream()
                .mapToDouble(d -> d)
                .summaryStatistics();

        return String.format("%3d: Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c",
                day, temps.size(), stats.getMin(), type, stats.getMax(), type, stats.getAverage(), type);
    }

}

class DailyTempFactory {
    public static DailyMeasurement createDailyTemp(String line) {
        String[] tokens = line.split("\\s++");

        int day = Integer.parseInt(tokens[0]);
        List<String> tempParts = List.of(tokens[1].split(""));
        String type = tempParts.contains("C") ? "C" : "F";

        List<Double> temps = parse(tokens, type);

        return new DailyMeasurement(day, temps);
    }

    private static List<Double> parse(String[] tokens, String type) {
        List<Double> temps = new ArrayList<>();
        for (int i = 1; i < tokens.length; i++) {
            String temp = tokens[i].split(type)[0];
            temps.add(
                    type.equals("C") ? Double.parseDouble(temp) : convertToCelsius(temp)
            );
        }
        return temps;
    }

    private static double convertToCelsius(String temp) {
        double t = Double.parseDouble(temp);
        return (t - 32) * 5.0 / 9.0;
    }
}

class DailyTemperatures {
    private Set<DailyMeasurement> measurements;

    public DailyTemperatures() {

    }


    public void readTemperatures(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        measurements = br.lines()
                .map(DailyTempFactory::createDailyTemp)
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(DailyMeasurement::getDay))));
    }

    public void writeDailyStats(PrintStream os, char type) {
        PrintWriter pw = new PrintWriter(os);

        measurements.forEach(dm -> pw.println(dm.printStats(type)));
        pw.flush();
    }
}


public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        dailyTemperatures.readTemperatures(System.in);
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
    }
}
```