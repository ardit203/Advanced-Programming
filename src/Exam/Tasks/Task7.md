You need to implement a class `TimeTable` which reads from an input stream (standard input, file, …) data for times in 24-hour format. All times are separated by a single space, and inside each time the hours and minutes can be separated by **:** or **.**.
Example of the data format:

`11:15 0.45 23:12 15:29 18.46`

Your task is to implement the methods:

* `TimeTable()` – default constructor
* `void readTimes(InputStream inputStream)` – method for reading the data
* `void writeTimes(OutputStream outputStream, TimeFormat format)` – method that prints all times sorted in ascending order in the given format (24-hour or AM/PM).

The reading method `readTimes` throws an exception of type `UnsupportedFormatException` if the times are separated by something other than **:** or **.**, and `InvalidTimeException` if the time (hours or minutes) is outside the allowed range (0–23, 0–59).
Both exceptions, in their `getMessage()` method, must return the input token that caused the exception.
All times read up to the moment when one of these two exceptions is thrown must remain stored.

---

**Rules for conversion from 24-hour format to AM/PM:**

* for the first hour of the day (0:00 – 0:59), add 12 and mark it as `"AM"`
* from 1:00 to 11:59, just mark it as `"AM"`
* from 12:00 to 12:59, just mark it as `"PM"`
* from 13:00 to 23:59, subtract 12 and mark it as `"PM"`


### Starter code
```java
public class TimesTest {

	public static void main(String[] args) {
		TimeTable timeTable = new TimeTable();
		try {
			timeTable.readTimes(System.in);
		} catch (UnsupportedFormatException e) {
			System.out.println("UnsupportedFormatException: " + e.getMessage());
		} catch (InvalidTimeException e) {
			System.out.println("InvalidTimeException: " + e.getMessage());
		}
		System.out.println("24 HOUR FORMAT");
		timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
		System.out.println("AM/PM FORMAT");
		timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
	}

}

enum TimeFormat {
	FORMAT_24, FORMAT_AMPM
}
```


### Solution
```java
import java.util.Comparator;
import java.util.List;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class UnsupportedFormatException extends RuntimeException {
    public UnsupportedFormatException(String time) {
        super(time);
    }
}


class InvalidTimeException extends RuntimeException {
    public InvalidTimeException(String time) {
        super(time);
    }
}

class TimeTable {
    List<MyTime> times;

    public TimeTable(){
        this.times = new ArrayList<>();
    }

    public void readTimes(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        br.lines().forEach(l -> {
            String [] tokens = l.split(" ");
            for (int i = 0; i < tokens.length; i++) {
                String [] parts = tokens[i].split(":");
                if(parts.length == 1){
                    parts = tokens[i].split("\\.");
                    if(parts.length == 1){
                        throw new UnsupportedFormatException(parts[0]);
                    }
                }
                times.add(new MyTime(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            }
        });
    }

    public void writeTimes(PrintStream out, TimeFormat timeFormat) {
        PrintWriter pw = new PrintWriter(out);
        if(timeFormat == TimeFormat.FORMAT_24){
            times.stream().sorted().forEach(pw::println);
        }else {
            times.stream().sorted().forEach(t -> pw.println(t.convertToAMPM()));
        }
        pw.flush();
    }
}


class MyTime implements Comparable<MyTime>{
    int hour;
    int minute;

    public MyTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
    
    public String formatMinute(){
        if(minute>=0 && minute<=9){
            return "0" + minute;
        }
        return String.valueOf(minute);
    }

    public String convertToAMPM() {
        int newHour = hour;
        String end = "AM";
        if (hour == 0) {
            newHour = 12;
            end = "AM";
        }

        if (hour == 12) {
            end = "PM";
        }

        if (hour > 12 && hour < 24) {
            newHour -= 12;
            end = "PM";
        }
        
        return String.format("%2d:%s %s", newHour, formatMinute(), end);
    }

    @Override
    public String toString() {
        return String.format("%2d:%s", hour, formatMinute());
    }

    @Override
    public int compareTo(MyTime o) {
        return Comparator.comparing(MyTime::getHour).thenComparing(MyTime::getMinute).compare(this, o);
    }
}



public class TimesTest {

	public static void main(String[] args) {
		TimeTable timeTable = new TimeTable();
		try {
			timeTable.readTimes(System.in);
		} catch (UnsupportedFormatException e) {
			System.out.println("UnsupportedFormatException: " + e.getMessage());
		} catch (InvalidTimeException e) {
			System.out.println("InvalidTimeException: " + e.getMessage());
		}
		System.out.println("24 HOUR FORMAT");
		timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
		System.out.println("AM/PM FORMAT");
		timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
	}

}

enum TimeFormat {
	FORMAT_24, FORMAT_AMPM
}
```