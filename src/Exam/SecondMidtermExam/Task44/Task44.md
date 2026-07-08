Implement a class for an event calendar `EventCalendar`. Each event is defined with:

* name
* location
* time (`LocalDateTime`).

The class should provide the following functionalities:

* `public EventCalendar(int year)` – constructor with one argument, the year of the calendar
* `public void addEvent(String name, String location, LocalDateTime date)` – adds a new event given with name, location and time. If the year of the event does not match the year of the calendar, an exception of type `WrongDateException` should be thrown with the message `Wrong date: [date]`.
* `public void listEvents(Date date)` – prints all events on a given date (day), sorted by their time in ascending order (if two events have the same time, they are sorted lexicographically by name). Getting the collection of events for a given date must be in constant time *O(1)*, and printing in linear time *O(n)* (no sorting, just iteration)! The format for printing an event is
  `dd MMM, YYY HH:mm at [location], [name]`.
* `public void listByMonth()` – prints all months (1–12) with the number of events in that month.


### Starter code
```java
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventCalendarTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int year = scanner.nextInt();
        scanner.nextLine();
        EventCalendar eventCalendar = new EventCalendar(year);
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            String name = parts[0];
            String location = parts[1];
            LocalDateTime date = LocalDateTime.parse(parts[2], fmt);
//            Date date = df.parse(parts[2]);
            try {
                eventCalendar.addEvent(name, location, date);
            } catch (WrongDateException e) {
                System.out.println(e.getMessage());
            }
        }
        LocalDateTime date = LocalDateTime.parse(scanner.nextLine(), fmt);
//        Date date = df.parse(scanner.nextLine());
        eventCalendar.listEvents(date);
        eventCalendar.listByMonth();
    }
}
```

### Solution
```java
// package Exam.SecondMidtermExam.Task44;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


class WrongDateException extends RuntimeException {
    public WrongDateException(LocalDateTime date) {
        super(String.format("Wrong date: %s", DateHelpers.toExceptionDate(date)));
    }
}

class DateHelpers {
    public static String parse(LocalDateTime dateTime) {
        String str = String.format("%d-%d", dateTime.getDayOfMonth(), dateTime.getMonthValue());
        return str;
    }

    public static String toExceptionDate(LocalDateTime dateTime) {
        String month = getMonthOrDay(dateTime.getMonth().toString());
        String day = getMonthOrDay(dateTime.getDayOfWeek().toString());

        return String.format("%s %s %d %02d:%02d:%02d UTC %d",
                day, month, dateTime.getDayOfMonth(),
                dateTime.getHour(),
                dateTime.getMinute(),
                dateTime.getSecond(),
                dateTime.getYear());
    }

    public static String toNormalDate(LocalDateTime dateTime) {
        String month = getMonthOrDay(dateTime.getMonth().toString());
        return String.format("%d %s, %d %02d:%02d",
                dateTime.getDayOfMonth(), month, dateTime.getYear(), dateTime.getHour(), dateTime.getMinute());
    }

    private static String getMonthOrDay(String dateTime) {
        String monthStr = dateTime.toLowerCase();
        return Character.toUpperCase(monthStr.charAt(0)) + monthStr.substring(1, 3);
    }
}

class Event implements Comparable<Event> {
    private String name;
    private String location;
    private LocalDateTime date;

    public Event(String name, String location, LocalDateTime date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Event o) {
        int compare1 = this.date.compareTo(o.date);
        if (compare1 == 0) return name.compareTo(o.name);
        return compare1;
    }

    @Override
    public String toString() {
        return String.format("%s at %s, %s", DateHelpers.toNormalDate(date), location, name);
    }
}


class EventCalendar {
    private int year;
    private Map<String, Set<Event>> events;

    public EventCalendar(int year) {
        this.year = year;
        this.events = new HashMap<>();
    }


    public void addEvent(String name, String location, LocalDateTime date) {
        if (date.getYear() != year) {
            throw new WrongDateException(date);
        }

        events.computeIfAbsent(DateHelpers.parse(date), k -> new TreeSet<>()).add(new Event(name, location, date));
    }

    public void listEvents(LocalDateTime date) {
        Set<Event> eventsOnDate = events.get(DateHelpers.parse(date));
        if (eventsOnDate == null) {
            System.out.println("No events on this day!");
            return;
        }
        eventsOnDate.forEach(System.out::println);
    }

    public void listByMonth() {
        Map<Integer, Long> byMonth = events.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        e -> e.getDate().getMonthValue(),
                        TreeMap::new,
                        Collectors.counting()
                ));
        for (int i = 1; i < 12; i++) {
            byMonth.putIfAbsent(i, 0L);
        }

        byMonth.forEach((k, v) -> System.out.printf("%d : %d\n", k, v));
    }


}

public class EventCalendarTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int year = scanner.nextInt();
        scanner.nextLine();
        EventCalendar eventCalendar = new EventCalendar(year);
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            String name = parts[0];
            String location = parts[1];
            LocalDateTime date = LocalDateTime.parse(parts[2], fmt);
//            Date date = df.parse(parts[2]);
            try {
                eventCalendar.addEvent(name, location, date);
            } catch (WrongDateException e) {
                System.out.println(e.getMessage());
            }
        }
        LocalDateTime date = LocalDateTime.parse(scanner.nextLine(), fmt);
//        Date date = df.parse(scanner.nextLine());
        eventCalendar.listEvents(date);
        eventCalendar.listByMonth();
    }
}
```