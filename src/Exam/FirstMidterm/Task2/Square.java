package Exam.FirstMidterm.Task2;

public class Square extends Shape{

    public Square(int length){
        super(length);
    }

    @Override
    public double area() {
        return getLength()*getLength();
    }
}
