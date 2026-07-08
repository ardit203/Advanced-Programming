package Exercises._01_OOP.Calculator;

public class InvalidOperationException extends Exception {
    public InvalidOperationException(String operator) {
        super(String.format("%s is an unknown operator", operator));
    }
}
