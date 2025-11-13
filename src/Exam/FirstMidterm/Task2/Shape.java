package Exam.FirstMidterm.Task2;

public abstract class Shape {
    private int length;

    public Shape(int length){
        this.length = length;
    }

    public abstract double area();

    public int getLength(){
        return length;
    }
}
