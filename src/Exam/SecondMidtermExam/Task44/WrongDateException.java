package Exam.SecondMidtermExam.Task44;

import java.time.LocalDateTime;

public class WrongDateException extends RuntimeException {
    public WrongDateException(LocalDateTime date) {
        super(String.format("Wrong date: %s", DateHelpers.toExceptionDate(date)));
    }
}