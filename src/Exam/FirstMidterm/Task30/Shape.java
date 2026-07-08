package Exam.FirstMidterm.Task30;

public abstract class Shape implements Comparable<Shape> {
    protected String id;

    public Shape(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract double area();

    public abstract double perimeter();

    public abstract void scale(double coefficient);

    @Override
    public int compareTo(Shape other) {
        return Double.compare(this.area(), other.area());
    }
}
