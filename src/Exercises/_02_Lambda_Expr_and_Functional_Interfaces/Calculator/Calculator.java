package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Calculator;


public class Calculator {
    private double result;

    public Calculator() {
        result = 0.0;
        System.out.println("Calculator is on!");
    }

    public void init() {
        System.out.printf("result = %f\n", result);

    }

    public double getResult() {
        return result;
    }

    public void execute(String operator, double value) throws InvalidOperationException {
        Operation op = CalculatorFactory.getOperation(operator);
        result = op.apply(result, value);
        System.out.printf("result %s %.2f = %.2f\n", operator, value, result);
    }

    @Override
    public String toString() {
        return String.format("updated result = %.2f", result);
    }
}

