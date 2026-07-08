package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Calculator;

public class IllegalOperationException extends RuntimeException {
    public IllegalOperationException() {
        super("Division by 0 is not allowed!");
    }
}
