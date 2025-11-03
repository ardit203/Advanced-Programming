package Lab.Lab2.Task7;

@SuppressWarnings("unchecked")
public class ComplexNumber<T extends Number, U extends Number> implements Comparable {
    private T real;
    private U imaginary;

    public ComplexNumber(T real, U imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public T getReal() {
        return real;
    }

    public U getImaginary() {
        return imaginary;
    }

    public double modul() {
        double power = Math.pow(real.doubleValue(), 2) + Math.pow(imaginary.doubleValue(), 2);
        return Math.sqrt(power);
    }

    @Override
    public String toString() {
        String sign = "+";
        if (imaginary.doubleValue() < 0) {
            sign = "";
        }
        return String.format("%.2f%s%.2fi", real.doubleValue(), sign, imaginary.doubleValue());
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(Object o) {
        ComplexNumber<T, U> c = (ComplexNumber<T, U>) o;
        return Double.compare(modul(), c.modul());
    }
}