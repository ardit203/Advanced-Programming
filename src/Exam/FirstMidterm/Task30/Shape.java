package Exam.FirstMidterm.Task30;

import java.util.Comparator;

public abstract class Shape implements Comparable<Shape>{
    private String id;

    public Shape(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract double perimeter();

    public abstract double area();

    public abstract void scale(double coef);

    @Override
    public int compareTo(Shape other){
        return Comparator.comparingDouble(Shape::perimeter).compare(this, other);
    }
}
