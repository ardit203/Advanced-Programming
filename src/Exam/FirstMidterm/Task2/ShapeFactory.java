package Exam.FirstMidterm.Task2;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {
    public static Canvas createShape(String line, double maxArea){
        String [] tokens = line.split("\\s++");

        String id = tokens[0];
        List<Shape> shapes = new ArrayList<>();
        for (int i = 1; i < tokens.length - 1; i+=2) {
            Shape s = null;
            int length = Integer.parseInt(tokens[i+1]);
            if(tokens[i].equalsIgnoreCase("c")){
                s = new Circle(length, ShapeType.CIRCLE);
            } else if (tokens[i].equalsIgnoreCase("s")) {
                s = new Square(length, ShapeType.SQUARE);
            }

            if(s.area() > maxArea){
                throw new InvalidCanvasException(id, maxArea);
            }
            shapes.add(s);
        }

        return new Canvas(id, shapes);
    }
}
