package Lab.Lab2.Task5;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class Scheduler<T> {
    private Timestamp<T>[] timestamps;
    private int size;

    @SuppressWarnings("unchecked")
    public Scheduler() {
        this.timestamps = (Timestamp<T>[]) new Timestamp[20];
        this.size = 0;
    }

    private void resize() {
        timestamps = Arrays.copyOf(timestamps, timestamps.length * 2);
    }

    public void add(Timestamp<T> t) {
        if (size == timestamps.length) {
            resize();
        }
        timestamps[size++] = t;
    }

    public boolean remove(Timestamp<T> t) {
        for (int i = 0; i < size; i++) {
            if (timestamps[i].equals(t)) {
                System.arraycopy(timestamps, i + 1, timestamps, i, size - i - 1);
                timestamps[--size] = null;
                return true;
            }
        }
        return false;
    }

    public Timestamp<T> next() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp<T> best = null;
        for (int i = 0; i < size; i++) {
            Timestamp<T> curr = timestamps[i];
            if (curr != null && curr.getTime().isAfter(now)) {
                if (best == null || curr.getTime().isBefore(best.getTime())) best = curr;
            }
        }
        return best;
    }

    public Timestamp<T> last() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp<T> best = null;
        for (int i = 0; i < size; i++) {
            Timestamp<T> curr = timestamps[i];
            if (curr != null && curr.getTime().isBefore(now)) {
                if (best == null || curr.getTime().isAfter(best.getTime())) best = curr;
            }
        }
        return best;
    }

    public List<Timestamp<T>> getAll(LocalDateTime begin, LocalDateTime end) {
        List<Timestamp<T>> all = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Timestamp<T> curr = timestamps[i];
            if (curr == null) continue;
            LocalDateTime t = curr.getTime();
            if (t.isAfter(begin) && t.isBefore(end)) {
                all.add(curr);
            }
        }
        return all;
    }

    //Additional Requirement
    public static <T, R> Scheduler<R> map(Scheduler<T> source, Function<Timestamp<T>, R> mapper, Predicate<Timestamp<T>> filter) {
        Scheduler<R> schd = new Scheduler<>();
        schd.timestamps = (Timestamp[]) Arrays.stream(source.timestamps).filter(filter).map(mapper).toArray();

        return schd;
    }


    public static <T> long countIf(Scheduler<T> source, Predicate<Timestamp<T>> predicate) {
        return Arrays.stream(source.timestamps).filter(predicate).count();
    }

    public static <T> Scheduler merge(Scheduler<? extends T> first, Scheduler<? extends T> second) {
        Scheduler<T> schd = new Scheduler<>();

        for (int i = 0; i < first.size; i++) {
            schd.add(new Timestamp<>(first.timestamps[i].getTime(), (T) first.timestamps[i].getElement()));
        }

        for (int i = 0; i < second.size; i++) {
            schd.add(new Timestamp<>(second.timestamps[i].getTime(), (T) second.timestamps[i].getElement()));
        }
        return schd;
    }
}