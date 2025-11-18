package Exam.FirstMidterm.Task30;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String id, double width, double height) {
        super(id);
        this.width = width;
        this.height = height;
    }

    @Override
    public double perimeter() {
        return 2 * width + 2 * height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public void scale(double coef) {
        width *= coef;
        height *= coef;
    }

    @Override
    public String toString() {
        return String.format("Rectangle: -> Sides: %.2f, %.2f Area: %.2f Perimeter: %.2f"
                , width, height, area(), perimeter());
    }
}
