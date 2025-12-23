package Exam.FirstMidterm.Task6;

import java.util.ArrayList;
import java.util.List;


public class Canvas {
    List<Shape> shapes;


    public Canvas() {
        this.shapes = new ArrayList<>();
    }

    public void add(String id, Color color, float radius) {
        add(new Circle(id, color, radius));
    }

    public void add(String id, Color color, float width, float height) {
        add(new Rectangle(id, color, width, height));
    }

    public void scale(String id, float scaleFactor) {
        Shape shape = shapes.stream()
                .filter(s -> s.getId().equals(id)).findFirst()
                .orElse(null);
        if (shape != null) {
            shapes.remove(shape);
            shape.scale(scaleFactor);
            add(shape);
        }
    }

    private void add(Shape shape) {
        int index = -1;
        for (int i = 0; i < shapes.size(); i++) {
            if (shape.compareTo(shapes.get(i)) > 0) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            shapes.add(shape);
            return;
        }

        shapes.add(shapes.get(shapes.size() - 1));

        for (int i = shapes.size() - 1; i > index; i--) {
            shapes.set(i, shapes.get(i - 1));
        }
        shapes.set(index, shape);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        shapes.forEach(sb::append);
        return sb.toString();
    }
}
