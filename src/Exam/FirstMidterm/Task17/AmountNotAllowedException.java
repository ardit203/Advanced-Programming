package Exam.FirstMidterm.Task17;

public class AmountNotAllowedException extends RuntimeException{
    public AmountNotAllowedException(int sum) {
        super(String.format("Receipt with amount %d is not allowed to be scanned", sum));
    }
}
