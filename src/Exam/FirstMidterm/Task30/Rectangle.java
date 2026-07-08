package Exam.FirstMidterm.Task30;

public class Rectangle extends Square {
    private double height;

    public Rectangle(String id, double base, double height) {
        super(id, base);
        this.height = height;
    }

    @Override
    public double area() {
        return base * height;
    }

    @Override
    public double perimeter() {
        return 2 * base + 2 * height;
    }

    @Override
    public void scale(double coefficient) {
        base *= coefficient;
        height *= coefficient;
    }

    @Override
    public String toString() {
        return String.format("Rectangle: -> Sides: %.2f, %.2f Area: %.2f Perimeter: %.2f", base, height, area(), perimeter());
    }
}
