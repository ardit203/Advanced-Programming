package Exam.FirstMidterm.Task12;

public class GenericFraction<T extends Number, U extends Number> {
    private T numerator;
    private U denominator;

    public GenericFraction(T numerator, U denominator) {
        if (denominator.equals(0)) {
            throw new ZeroDenominatorException();
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    private static GenericFraction<Double, Double> simplify(double num, double den){
        double n = Math.min(num, den);
        double numerator = num;
        double denominator = den;

        while (n > 0){
            if(num % n == 0 && den % n == 0){
                numerator = num / n;
                denominator = den / n;
                return new GenericFraction<>(numerator, denominator);
            }
            n--;
        }
        return new GenericFraction<>(numerator, denominator);
    }

    public GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) {
        double first = numerator.doubleValue() * gf.denominator.doubleValue();
        double second = gf.numerator.doubleValue() * denominator.doubleValue();
        double num = first + second;
        double den = denominator.doubleValue() * gf.denominator.doubleValue();

        return simplify(num, den);
    }

    public double toDouble() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override
    public String toString() {
        return String.format("%.2f / %.2f"
                , numerator.doubleValue(), denominator.doubleValue());
    }
}
