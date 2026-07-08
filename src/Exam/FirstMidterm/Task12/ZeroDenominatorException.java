package Exam.FirstMidterm.Task12;

public class ZeroDenominatorException extends RuntimeException{
    public ZeroDenominatorException() {
        super("Denominator cannot be zero");
    }
}
