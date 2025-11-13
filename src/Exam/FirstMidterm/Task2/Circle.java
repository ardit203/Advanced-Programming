package Exam.FirstMidterm.Task2;

public class Circle extends Shape {

    public Circle(int length) {
        super(length);
    }

    @Override
    public double area() {
        return Math.PI * getLength() * getLength();
    }
}
