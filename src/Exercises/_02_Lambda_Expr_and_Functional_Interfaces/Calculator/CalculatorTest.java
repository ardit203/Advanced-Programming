package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Calculator;

import java.util.Scanner;

public class CalculatorTest {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Calculator calculator = new Calculator();
            calculator.init();

            while (true) {
                String line = scanner.nextLine();
                if (line.equals("r")) {
                    System.out.printf("final result = %.2f\n", calculator.getResult());
                    break;
                }
                String[] parts = line.split("\\s++");
                if (parts.length < 2) {
                    System.out.println("Please enter: <operator>...<number>");
                    continue;
                }

                String operator = parts[0];
                double value = Double.parseDouble(parts[1]);

                try {
                    calculator.execute(operator, value);
                } catch (InvalidOperationException | IllegalOperationException e) {
                    System.out.println(e.getMessage());
                }

            }
            System.out.print("Want to continue (y/n)");

            if (scanner.nextLine().equalsIgnoreCase("n")) {
                break;
            }
        }
    }
}
