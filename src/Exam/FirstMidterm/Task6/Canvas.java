package Exam.FirstMidterm.Task6;

import java.util.Arrays;

public class Canvas {
    private Shape[] shapes;
    private int size;

    public Canvas() {
        this.shapes = new Shape[10];
        this.size = 0;
    }

    private int index(float weight) {
        for (int i = 0; i < size; i++) {
            if (shapes[i].weight() < weight) {
                return i;
            }
        }
        return size;
    }

    private void shift(Shape s) {
        int j = index(s.weight());
        for (int i = size; i > j; i--) {
            shapes[i] = shapes[i - 1];
        }
        shapes[j] = s;
        size++;
    }

    private void resize() {
        if (size == shapes.length) {
            shapes = Arrays.copyOf(shapes, size * 2);
        }
    }

    void add(String id, Color color, float radius) {
        resize();
        Circle circle = new Circle(id, color, radius);
        if(size == 0){
            shapes[size++] = circle;
            return;
        }
        shift(circle);
    }

    void add(String id, Color color, float width, float height) {
        resize();
        Rectangle rectangle = new Rectangle(id, color, width, height);
        if(size == 0){
            shapes[size++] = rectangle;
            return;
        }
        shift(rectangle);
    }

    void scale(String id, float scaleFactor) {
        Shape s = null;

        int index = -1;
        for (int i = 0; i < size; i++) {
            if (shapes[i].getId().equals(id)) {
                s = shapes[i];
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        for (int i = index; i < size - 1; i++) {
            shapes[i] = shapes[i + 1];
        }

        shapes[--size] = null;
        s.scale(scaleFactor);
        shift(s);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Canvas").append("\n\n");

        for (int i = 0; i < size; i++) {
            sb.append(shapes[i]);
        }
        return sb.toString();
    }
}
