At a weather station, every 5 minutes data arrives with information about the weather conditions (temperature, humidity, wind, visibility, time).
Example of such data:

* temperature: 13°C
* humidity: 98%
* wind: 11.2 km/h
* visibility: 14 km
* time: 28.12.2013 14:37:55 (dd.MM.yyyy HH:mm:ss)


You need to implement a class **WeatherStation** that stores weather condition data for the last **x days** (when adding a new measurement, all measurements older than x days from the new one must be deleted). Also, if the timestamp of the new measurement differs by less than **2.5 minutes** from the timestamp of any previously added measurement, that measurement must be ignored (not added).

You must implement the following methods in the class **WeatherStation**:

* **`WeatherStation(int days)`** – constructor that sets the number of days to store measurements
* **`public void addMeasurement(float temperature, float wind, float humidity, float visibility, Date date)`** – adds a new measurement
* **`public int total()`** – returns the total number of stored measurements
* **`public void status(Date from, Date to)`** – prints all measurements in the period **from → to**, sorted by date in ascending order, and finally prints the *average temperature* in that period.
  If no measurements exist in that period, this method throws a **RuntimeException** (built-in Java exception).

Example output format:

```
24.6 80.2 km/h 28.7% 51.7 km Tue Dec 17 23:40:15 CET 2013
23.5 32.2 km/h 16.5% 187.2 km Tue Dec 17 23:45:15 CET 2013
27.3 11.8 km/h 17.9% 135.4 km Tue Dec 17 23:50:15 CET 2013
Average temperature: 20.43
```


### Starter code
```java
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WeatherStationTest {
	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
		WeatherStation ws = new WeatherStation(n);
		while (true) {
			String line = scanner.nextLine();
			if (line.equals("=====")) {
				break;
			}
			String[] parts = line.split(" ");
			float temp = Float.parseFloat(parts[0]);
			float wind = Float.parseFloat(parts[1]);
			float hum = Float.parseFloat(parts[2]);
			float vis = Float.parseFloat(parts[3]);
			line = scanner.nextLine();
			Date date = df.parse(line);
			ws.addMeasurment(temp, wind, hum, vis, date);
		}
		String line = scanner.nextLine();
		Date from = df.parse(line);
		line = scanner.nextLine();
		Date to = df.parse(line);
		scanner.close();
		System.out.println(ws.total());
		try {
			ws.status(from, to);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}
}
```

### Solution
```java
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Locale;
import java.util.TimeZone;


public class WeatherStationTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
        WeatherStation ws = new WeatherStation(n);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("=====")) {
                break;
            }
            String[] parts = line.split(" ");
            float temp = Float.parseFloat(parts[0]);
            float wind = Float.parseFloat(parts[1]);
            float hum = Float.parseFloat(parts[2]);
            float vis = Float.parseFloat(parts[3]);
            line = scanner.nextLine();
            Date date = df.parse(line);
            ws.addMeasurment(temp, wind, hum, vis, date);
        }
        String line = scanner.nextLine();
        Date from = df.parse(line);
        line = scanner.nextLine();
        Date to = df.parse(line);
        scanner.close();
        System.out.println(ws.total());
        try {
            ws.status(from, to);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}

class Measurement implements Comparable<Measurement>{
    private float temp;
    private float wind;
    private float hum;
    private float vis;
    private Date date;

    public Measurement(float temp, float wind, float hum, float vis, Date date) {
        this.temp = temp;
        this.wind = wind;
        this.hum = hum;
        this.vis = vis;
        this.date = date;
    }

    @Override
    public int compareTo(Measurement other) {
        return this.date.compareTo(other.date);
    }

    public float getTemp() {
        return temp;
    }

    public float getWind() {
        return wind;
    }

    public float getHum() {
        return hum;
    }

    public Date getDate() {
        return date;
    }

    public float getVis() {
        return vis;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s",temp, wind, hum, vis, sdf.format(date));
    }
}

class WeatherStation {
    private int days;
    private List<Measurement> measurements;

    public WeatherStation(int days) {
        this.days = days;
        this.measurements = new ArrayList<>();
    }


    public void addMeasurment(float temp, float wind, float hum, float vis, Date date) {
        if (measurements.isEmpty()) {
            measurements.add(new Measurement(temp, wind, hum, vis, date));
        }

        if (differsBy2andHalfMins(date)) return;

        measurements.add(new Measurement(temp, wind, hum, vis, date));

        removeOlderThanXDays(date);
    }


    public boolean differsBy2andHalfMins(Date date) {
        return measurements.stream().anyMatch(m -> {
            long diffInMs = date.getTime() - m.getDate().getTime();
            double diffInS = diffInMs / (1000.0 * 60);
            if (diffInS <= 2.5) {
                return true;
            }
            return false;
        });
    }

    public void removeOlderThanXDays(Date date) {
        //The date x days before the new date
        Date before = new Date(date.getTime() - (long) days * 1440 * 1000 * 60);
        measurements.removeIf(m -> m.getDate().before(before) || m.getDate().equals(before));
    }

    public int total() {
        return measurements.size();
    }

    public void status(Date from, Date to) {
        List<Measurement> status = measurements.stream().filter(m -> {
                    return m.getDate().after(from) &&
                            m.getDate().before(to) ||
                            m.getDate().equals(from) ||
                            m.getDate().equals(to);
                })
                .sorted()
                .collect(Collectors.toList());
        if(status.isEmpty()){
            throw new RuntimeException();
        }
        double avgTemp = status.stream()
                .mapToDouble(Measurement::getTemp)
                .average()
                .orElse(0);

        status.forEach(System.out::println);
        System.out.printf("Average temperature: %.2f\n", avgTemp);

    }
}
```