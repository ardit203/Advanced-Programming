package Exam.FirstMidterm.Task12;

public class GenericFraction<T extends Number, U extends Number> {
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
