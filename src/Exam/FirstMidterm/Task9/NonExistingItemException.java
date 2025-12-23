package Exam.FirstMidterm.Task9;

public class NonExistingItemException extends RuntimeException{
    public NonExistingItemException(int id) {
        super(String.format("Item with id %d doesn't exist", id));
    }
}
