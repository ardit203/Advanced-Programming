package Exam.FirstMidterm.Task30;

public class Circle extends Shape {
    private double radius;

    public Circle(String id, double radius) {
        super(id);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void scale(double coefficient) {
        radius *= coefficient;
    }

    @Override
    public String toString() {
        return String.format("Circle -> Radius: %.2f Area: %.2f Perimeter: %.2f", radius, area(), perimeter());
    }
}
