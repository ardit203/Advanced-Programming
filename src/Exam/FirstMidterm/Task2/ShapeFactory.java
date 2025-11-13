package Exam.FirstMidterm.Task2;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {
    public static Canvas create(String line, double maxArea) {
        String[] tokens = line.split("\\s++");

        String id = tokens[0];
        List<Shape> shapes = new ArrayList<>();
        for (int i = 1; i < tokens.length - 1; i += 2) {
            int length = Integer.parseInt(tokens[i + 1]);
            Shape shape;

            if (tokens[i].equals("C")) {
                shape = new Circle(length);
            } else {
                shape = new Square(length);
            }

            if (shape.area() > maxArea) {
                throw new InvalidCanvasException(id, maxArea);
            }

            shapes.add(shape);
        }
        return new Canvas(id, shapes);
    }
}
