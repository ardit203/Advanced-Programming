package Exercises._03_Generics.Priority_Queue;

public class Item<T> implements Comparable<Item<T>>{

    private T item;
    private int priority;

    public Item(T item, int priority) {
        this.item = item;
        this.priority = priority;
    }

    public T getItem() {
        return item;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("[%s -- %d]", item, priority);
    }

    @Override
    public int compareTo(Item<T> o) {
        return Integer.compare(priority, o.priority);
    }
}