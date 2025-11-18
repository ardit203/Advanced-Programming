# Lab Exercise 2 - Advanced Programming

## Task 7


You need to develop a generic class for working with complex numbers, called `ComplexNumber`, that has two generic type parameters `T` and `U` and both parameters must inherit from `Number`.

The class `ComplexNumber` has two variables —
one representing the real part and the other representing the imaginary part.

It must implement the following methods:
- `ComplexNumber(T real, U imaginary)` – constructor that initializes both variables.

- `getReal(): T` – returns the real part.

- `getImaginary(): U` – returns the imaginary part.

- `modul(): double` – calculates the modulus (magnitude) of the complex number.

- `compareTo(ComplexNumber<?, ?> o)` – compares two complex numbers based on their moduli.

- `toString(): String` – prints the number in the following format: `2.30+3.00i`


Starter code:
```java
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class ComplexNumberTest {

	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int k = jin.nextInt();
		if ( k == 0 ) { //test simple functions int
			int r = jin.nextInt();int i = jin.nextInt();
			ComplexNumber<Integer, Integer> c = new ComplexNumber<Integer, Integer>(r, i);
			System.out.println(c);
			System.out.println(c.getReal());
			System.out.println(c.getImaginary());
			System.out.println(c.modul());
		}
		if ( k == 1 ) { //test simple functions float
			float r = jin.nextFloat();
			float i = jin.nextFloat();
			ComplexNumber<Float, Float> c = new ComplexNumber<Float, Float>(r, i);
			System.out.println(c);
			System.out.println(c.getReal());
			System.out.println(c.getImaginary());
			System.out.println(c.modul());
		}
		if ( k == 2 ) { //compareTo int
			LinkedList<ComplexNumber<Integer,Integer>> complex = new LinkedList<ComplexNumber<Integer,Integer>>();
			while ( jin.hasNextInt() ) {
				int r = jin.nextInt(); int i = jin.nextInt();
				complex.add(new ComplexNumber<Integer, Integer>(r, i));
			}
			System.out.println(complex);
			Collections.sort(complex);
			System.out.println(complex);
		}
		if ( k == 3 ) { //compareTo double
			LinkedList<ComplexNumber<Double,Double>> complex = new LinkedList<ComplexNumber<Double,Double>>();
			while ( jin.hasNextDouble() ) {
				double r = jin.nextDouble(); double i = jin.nextDouble();
				complex.add(new ComplexNumber<Double, Double>(r, i));
			}
			System.out.println(complex);
			Collections.sort(complex);
			System.out.println(complex);
		}
		if ( k == 4 ) { //compareTo mixed
			LinkedList<ComplexNumber<Double,Integer>> complex = new LinkedList<ComplexNumber<Double,Integer>>();
			while ( jin.hasNextDouble() ) {
				double r = jin.nextDouble(); int i = jin.nextInt();
				complex.add(new ComplexNumber<Double, Integer>(r, i));
			}
			System.out.println(complex);
			Collections.sort(complex);
			System.out.println(complex);
		}
	}
}
```

Solution:
```java
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

@SuppressWarnings("unchecked")
class ComplexNumber<T extends Number, U extends Number> implements Comparable<ComplexNumber<?,?>> {
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
    public int compareTo(ComplexNumber<?, ?> o) {
        return Double.compare(modul(), o.modul());
    }
}



public class ComplexNumberTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (k == 0) { //test simple functions int
            int r = jin.nextInt();
            int i = jin.nextInt();
            ComplexNumber<Integer, Integer> c = new ComplexNumber<Integer, Integer>(r, i);
            System.out.println(c);
            System.out.println(c.getReal());
            System.out.println(c.getImaginary());
            System.out.println(c.modul());
        }
        if (k == 1) { //test simple functions float
            float r = jin.nextFloat();
            float i = jin.nextFloat();
            ComplexNumber<Float, Float> c = new ComplexNumber<Float, Float>(r, i);
            System.out.println(c);
            System.out.println(c.getReal());
            System.out.println(c.getImaginary());
            System.out.println(c.modul());
        }
        if (k == 2) { //compareTo int
            LinkedList<ComplexNumber<Integer, Integer>> complex = new LinkedList<ComplexNumber<Integer, Integer>>();
            while (jin.hasNextInt()) {
                int r = jin.nextInt();
                int i = jin.nextInt();
                complex.add(new ComplexNumber<Integer, Integer>(r, i));
            }
            System.out.println(complex);
            Collections.sort(complex);
            System.out.println(complex);
        }
        if (k == 3) { //compareTo double
            LinkedList<ComplexNumber<Double, Double>> complex = new LinkedList<ComplexNumber<Double, Double>>();
            while (jin.hasNextDouble()) {
                double r = jin.nextDouble();
                double i = jin.nextDouble();
                complex.add(new ComplexNumber<Double, Double>(r, i));
            }
            System.out.println(complex);
            Collections.sort(complex);
            System.out.println(complex);
        }
        if (k == 4) { //compareTo mixed
            LinkedList<ComplexNumber<Double, Integer>> complex = new LinkedList<ComplexNumber<Double, Integer>>();
            while (jin.hasNextDouble()) {
                double r = jin.nextDouble();
                int i = jin.nextInt();
                complex.add(new ComplexNumber<Double, Integer>(r, i));
            }
            System.out.println(complex);
            Collections.sort(complex);
            System.out.println(complex);
        }
    }
}
```