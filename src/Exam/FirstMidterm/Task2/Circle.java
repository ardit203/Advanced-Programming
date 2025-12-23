package Exam.FirstMidterm.Task2;

public class Circle extends Shape {
    public Circle(double length, ShapeType type) {
        super(length, type);
    }

    @Override
    public double area() {
        return Math.PI * length * length;
    }
}
