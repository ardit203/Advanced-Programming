package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Calculator;

public class InvalidOperationException extends Exception {
    public InvalidOperationException(String operator) {
        super(String.format("%s is an unknown operator", operator));
    }
}
