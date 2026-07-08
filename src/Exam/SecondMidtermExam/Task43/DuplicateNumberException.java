package Exam.SecondMidtermExam.Task43;

public class DuplicateNumberException extends RuntimeException {
    public DuplicateNumberException(String number) {
        super(String.format("Duplicate number: %s", number));
    }
}