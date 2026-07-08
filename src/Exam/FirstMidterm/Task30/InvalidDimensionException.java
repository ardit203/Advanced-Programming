package Exam.FirstMidterm.Task30;

class InvalidDimensionException extends RuntimeException {
    InvalidDimensionException() {
        super("Dimension 0 is not allowed!");
    }
}