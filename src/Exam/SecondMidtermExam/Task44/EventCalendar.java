package Exam.SecondMidtermExam.Task44;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EventCalendar {
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