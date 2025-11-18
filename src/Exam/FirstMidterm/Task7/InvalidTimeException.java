package Exam.FirstMidterm.Task7;

public class InvalidTimeException extends RuntimeException {
    public InvalidTimeException(String time) {
        super(time);
    }
}
