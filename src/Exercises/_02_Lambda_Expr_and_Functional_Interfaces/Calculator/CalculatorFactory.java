package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Calculator;

interface Operation {
    double apply(double a, double b);
}

public class CalculatorFactory {

    private static final Operation ADD = (a, b) -> a + b;
    private static final Operation SUBTRACT = (a, b) -> a - b;
    private static final Operation MULTIPLY = (a, b) -> a * b;
    private static final Operation DIVIDE = (a, b) -> {
        if (b == 0.0) {
            throw new IllegalOperationException();
        }
        return a / b;
    };

    public static Operation getOperation(String operator) throws InvalidOperationException {
        if (operator.equals("+")) {
            return ADD;
        } else if (operator.equals("-")) {
            return SUBTRACT;
        } else if (operator.equals("*")) {
            return MULTIPLY;
        } else if (operator.equals("/")) {
            return DIVIDE;
        } else {
            throw new InvalidOperationException(operator);
        }
    }
}
