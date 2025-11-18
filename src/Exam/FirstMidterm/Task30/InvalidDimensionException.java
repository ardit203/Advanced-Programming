package Exam.FirstMidterm.Task30;

public class InvalidDimensionException extends RuntimeException {
    public InvalidDimensionException(int dimension) {
        super(String.format("Dimension %d is not allowed!", dimension));
    }
}
