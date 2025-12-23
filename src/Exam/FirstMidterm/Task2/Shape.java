package Exam.FirstMidterm.Task2;

public abstract class Shape {
    protected double length;
    protected ShapeType type;

    public Shape(double length, ShapeType type) {
        this.length = length;
        this.type = type;
    }

    public double getLength() {
        return length;
    }

    public abstract double area();

    public ShapeType getType(){
        return type;
    }
}
