package Exam.FirstMidterm.Task30;

public class Circle extends Shape {
    private double radius;

    public Circle(String id, double radius) {
        super(id);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double perimeter() {
        return Math.PI * 2 * radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double coef) {
        this.radius *= coef;
    }

    @Override
    public String toString() {
        return String.format("Circle -> Radius: %.2f Area: %.2f Perimeter: %.2f"
                ,radius, area(), perimeter());
    }
}
