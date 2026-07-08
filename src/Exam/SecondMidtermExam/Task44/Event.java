package Exam.SecondMidtermExam.Task44;

import java.time.LocalDateTime;

public class Event implements Comparable<Event> {
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