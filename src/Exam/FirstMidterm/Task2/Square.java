package Exam.FirstMidterm.Task2;

public class Square extends Shape {
    public Square(double length, ShapeType type) {
        super(length, type);
    }

    @Override
    public double area() {
        return length * length;
    }
}
