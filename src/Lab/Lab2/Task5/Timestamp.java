package Lab.Lab2.Task5;

import java.time.LocalDateTime;

public @SuppressWarnings("unchecked")
class Timestamp<T> implements Comparable<Timestamp<T>> {
    private final LocalDateTime time;
    private final T element;

    public Timestamp(LocalDateTime time, T element) {
        this.element = element;
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public T getElement() {
        return element;
    }

    @Override
    public boolean equals(Object obj) {
        Timestamp<T> other = (Timestamp<T>) obj;
        return this.compareTo(other) == 0;
    }

    @Override
    public String toString() {
        return time + " " + element;
    }

    @Override
    public int compareTo(Timestamp<T> o) {
        return this.time.compareTo(o.time);
    }
}