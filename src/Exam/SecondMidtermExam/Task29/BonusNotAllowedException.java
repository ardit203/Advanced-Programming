package Exam.SecondMidtermExam.Task29;

public class BonusNotAllowedException extends RuntimeException {
    public BonusNotAllowedException(String bonus) {
        super(String.format("Bonus of %s is not allowed", bonus));
    }
}