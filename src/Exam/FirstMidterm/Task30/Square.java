package Exam.FirstMidterm.Task30;

public class Square extends Shape {
    private double side;

    public Square(String id, double side) {
        super(id);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double perimeter() {
        return 4 * side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public void scale(double coef) {
        side *= coef;
    }

    @Override
    public String toString() {
        return String.format("Square: -> Side: %.2f Area: %.2f Perimeter: %.2f"
                , side, area(), perimeter());
    }
}
