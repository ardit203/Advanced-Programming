package Lab.Lab4.Task1;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class Scheduler<T> {
    Map<Date, T> map;

    public Scheduler() {
        map = new TreeMap<>();
    }
    public void add(Date d, T t) {
        map.putIfAbsent(d, t);
    }

    public boolean remove(Date d) {
        return map.remove(d) != null;
    }

    public T next() {
        Date now = new Date();
        Date next = map.keySet()
                .stream()
                .filter(date -> date.after(now))
                .min(Comparator.comparing(Date::getTime)).orElse(now);
        return map.get(next);
    }

    public T last() {
        Date now = new Date();
        Date last = map.keySet()
                .stream()
                .filter(date -> date.before(now))
                .max(Comparator.comparing(Date::getTime)).orElse(now);
        return map.get(last);
    }

    public ArrayList<T> getAll(Date begin, Date end) {
        return map.entrySet().stream()
                .filter(e -> e.getKey().after(begin) && e.getKey().before(end))
                .map(Map.Entry::getValue)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public T getFirst() {
        Date date = map.keySet().stream().findFirst().orElse(new Date());
        return map.get(date);
    }

    public T getLast() {
        Date date = map.keySet().stream().max(Comparator.comparing(Date::getTime)).orElse(new Date());
        return map.get(date);
    }
}