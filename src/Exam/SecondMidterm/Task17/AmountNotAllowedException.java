package Exam.SecondMidterm.Task17;

public class AmountNotAllowedException extends RuntimeException{
    public AmountNotAllowedException(int amount) {
        super(String.format("Receipt with amount %d is not allowed to be scanned", amount));
    }
}
