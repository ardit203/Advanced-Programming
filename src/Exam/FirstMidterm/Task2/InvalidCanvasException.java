package Exam.FirstMidterm.Task2;

public class InvalidCanvasException extends RuntimeException {
    public InvalidCanvasException(String id, double maxArea) {
        super(String.format("Canvas %s has a shape with area larger than %.2f", id, maxArea));
    }
}
