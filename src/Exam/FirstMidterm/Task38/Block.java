package Exam.FirstMidterm.Task38;

import java.util.Set;
import java.util.TreeSet;

class Block<T extends Comparable<T>> {
    private Set<T> elements;

    public Block() {
        this.elements = new TreeSet<>();
    }

    public Set<T> getElements() {
        return elements;
    }

    public void addElement(T element) {
        elements.add(element);
    }

    public void remove(T element) {
        elements.remove(element);
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}