package Exam.FirstMidterm.Task30;

public class Square extends Shape{
    protected double base;

    public Square(String id, double base) {
        super(id);
        this.base = base;
    }

    @Override
    public double area() {
        return base * base;
    }

    @Override
    public double perimeter() {
        return 4 * base;
    }

    @Override
    public void scale(double coefficient) {
        base *= coefficient;
    }

    @Override
    public String toString() {
        return String.format("Square: -> Side: %.2f Area: %.2f Perimeter: %.2f", base, area(), perimeter());
    }
}
