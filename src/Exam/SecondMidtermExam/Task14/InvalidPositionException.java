package Exam.SecondMidtermExam.Task14;

public class InvalidPositionException extends RuntimeException{
    public InvalidPositionException(int position) {
        super(String.format("Invalid position %d, alredy taken!", position));
    }
}
