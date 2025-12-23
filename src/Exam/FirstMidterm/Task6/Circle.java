package Exam.FirstMidterm.Task6;

public class Circle extends Shape{
    private float radius;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void scale(float scaleFactor) {
        radius *= scaleFactor;
    }

    @Override
    public float weight() {
        return (float) Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return String.format("C: %s\n",getRepresentation());
    }
}
