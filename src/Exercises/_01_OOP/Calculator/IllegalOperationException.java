package Exercises._01_OOP.Calculator;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException() {
        super("Division by 0 is not allowed!");
    }
}
