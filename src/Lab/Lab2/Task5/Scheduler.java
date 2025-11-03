package Lab.Lab2.Task5;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
}