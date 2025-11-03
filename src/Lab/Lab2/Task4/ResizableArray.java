package Lab.Lab2.Task4;

import java.util.Arrays;

public class ResizableArray<T> {
    private T[] elements;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ResizableArray() {
        this.size = 0;
        this.capacity = 10;
        this.elements = (T[]) new Object[this.capacity];
    }

    private void resize(int n) {
        capacity += n;
        elements = Arrays.copyOf(elements, capacity);
    }

    private int find(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void addElement(T element) {
        if (size == capacity) {
            resize(20);
        }
        elements[size++] = element;
    }

    public boolean removeElement(T element) {
        int index = find(element);
        if (index == -1) {
            return false;
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        elements[--size] = null;
        if (size + 40 < capacity) {
            resize(-20);
        }
        return true;
    }

    public boolean contains(T element) {
        return find(element) != -1;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int count() {
        return size;
    }

    public T elementAt(int idx) {
        if (idx < 0 || idx > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elements[idx];
    }

    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src) {
        int n = src.count();

        for (int i = 0; i < n; i++) {
            dest.addElement(src.elementAt(i));
        }
    }
}