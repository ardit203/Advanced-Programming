package Exam.FirstMidterm.Task6;

public abstract class Shape implements Stackable, Scalable, Comparable<Shape> {
    private String id;
    private Color color;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }


    public String getRepresentation() {
        return String.format("%-5s%-10s%10.2f", id, color, weight());
    }

    @Override
    public int compareTo(Shape other) {
        return Float.compare(this.weight(), other.weight());
    }
}
