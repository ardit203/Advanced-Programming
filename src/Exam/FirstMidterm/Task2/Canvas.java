package Exam.FirstMidterm.Task2;

import java.util.Comparator;
import java.util.List;

public class Canvas {
    private String canvasId;
    private List<Shape> shapes;

    public Canvas(String id, List<Shape> shapes) {
        this.canvasId = id;
        this.shapes = shapes;
    }

    public double sumArea() {
        return shapes.stream().mapToDouble(Shape::area).sum();
    }

    public int countShapesOfType(Class<?> type) {
        return (int) shapes.stream().filter(type::isInstance).count();
    }

    public double minArea() {
        return shapes.stream().mapToDouble(Shape::area).min().orElse(0);
    }

    public double maxArea() {
        return shapes.stream().mapToDouble(Shape::area).max().orElse(0);
    }

    public double avgArea() {
        return shapes.stream().mapToDouble(Shape::area).average().orElse(0);
    }

    @Override
    public String toString() {
        int circlesCount = countShapesOfType(Circle.class);
        int squaresCount = countShapesOfType(Square.class);
        double max = maxArea();
        double min = minArea();
        double avg = avgArea();

        return String.format("%s %d %d %d %.2f %.2f %.2f", canvasId, shapes.size(), circlesCount, squaresCount, min, max, avg);
    }
}
