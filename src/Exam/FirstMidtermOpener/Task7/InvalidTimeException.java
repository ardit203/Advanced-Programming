package Exam.FirstMidtermOpener.Task7;

public class InvalidTimeException extends RuntimeException {
    public InvalidTimeException(String time) {
        super(String.format("InvalidTimeException %s",time));
    }
}
