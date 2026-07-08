package Exam.SecondMidtermExam.Task33;

import java.time.LocalDateTime;

class DeadlineNotValidException extends RuntimeException {
    public DeadlineNotValidException(LocalDateTime deadline) {
        super(String.format("The deadline %s has already passed", deadline));
    }
}