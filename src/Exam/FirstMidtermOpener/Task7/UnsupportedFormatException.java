package Exam.FirstMidtermOpener.Task7;

public class UnsupportedFormatException extends RuntimeException {
    public UnsupportedFormatException(String time) {
        super(String.format("%s", time));
    }
}
