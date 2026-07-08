A generic class for working with fractions needs to be developed.
The class `GenericFraction` has two generic parameters `T` and `U`, which must be of some class that extends the class `Number`.

`GenericFraction` has two variables:

* numerator – the numerator
* denominator – the denominator

The following methods must be implemented:

* `GenericFraction(T numerator, U denominator)` – a constructor that initializes the numerator and denominator of the fraction.
  If we attempt to initialize a fraction with denominator value 0, an exception of type `ZeroDenominatorException` must be thrown.

* `GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf)` – adding two fractions.

* `double toDouble()` – returns the value of the fraction as a real (double) number.

* `toString(): String` – prints the fraction in the following format
  `[numerator] / [denominator]`, shortened (normalized) and each with two decimal places.


### Starter code
```java
import java.util.Scanner;

public class GenericFractionTest {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
        	GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
        	GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
        	GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch(ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }
        
        scanner.close();
    }

}
```

### Solution
```java
import java.util.Scanner;

public class GenericFractionTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double n1 = scanner.nextDouble();
        double d1 = scanner.nextDouble();
        float n2 = scanner.nextFloat();
        float d2 = scanner.nextFloat();
        int n3 = scanner.nextInt();
        int d3 = scanner.nextInt();
        try {
            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
            System.out.printf("%.2f\n", gfDouble.toDouble());
            System.out.println(gfDouble.add(gfFloat));
            System.out.println(gfInt.add(gfFloat));
            System.out.println(gfDouble.add(gfInt));
            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
        } catch(ZeroDenominatorException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }

}

class ZeroDenominatorException extends RuntimeException{
    public ZeroDenominatorException() {
        super("Denominator cannot be zero");
    }
}


class GenericFraction<T extends Number, U extends Number> {
    private T numerator;
    private U denominator;

    public GenericFraction(T numerator, U denominator) {
        this.numerator = numerator;
        this.denominator = denominator;

        if (denominator.doubleValue() == 0) {
            throw new ZeroDenominatorException();
        }
    }

    public GenericFraction<Double, Double> simplify(double numerator, double denominator) {
        double n = Math.min(numerator, denominator);

        while (n > 0) {
            if (numerator % n == 0 && denominator % n == 0) {
                numerator /= n;
                denominator /= n;
            }
            n--;
        }

        return new GenericFraction<>(numerator, denominator);
    }

    public GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) {
        double numerator = this.numerator.doubleValue() * gf.denominator.doubleValue()
                + gf.numerator.doubleValue() * this.denominator.doubleValue();

        double denominator = this.denominator.doubleValue() * gf.denominator.doubleValue();

        return simplify(numerator, denominator);
    }

    public double toDouble() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override
    public String toString() {
        return String.format("%.2f / %.2f", numerator.doubleValue(), denominator.doubleValue());
    }
}
```