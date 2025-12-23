Implement a generic class **`Triple`** (triple) that holds numeric values (three numbers). The class should implement:

* a constructor with 3 arguments,
* `double max()` – returns the largest of the three numbers
* `double average()` – returns the average of the three numbers
* `void sort()` – sorts the elements in ascending order
* override the `toString()` method so that it returns a formatted string with two decimal places for each element and a space between them.

### Starter code
```java
public class TripleTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		Triple<Integer> tInt = new Triple<Integer>(a, b, c);
		System.out.printf("%.2f\n", tInt.max());
		System.out.printf("%.2f\n", tInt.avarage());
		tInt.sort();
		System.out.println(tInt);
		float fa = scanner.nextFloat();
		float fb = scanner.nextFloat();
		float fc = scanner.nextFloat();
		Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
		System.out.printf("%.2f\n", tFloat.max());
		System.out.printf("%.2f\n", tFloat.avarage());
		tFloat.sort();
		System.out.println(tFloat);
		double da = scanner.nextDouble();
		double db = scanner.nextDouble();
		double dc = scanner.nextDouble();
		Triple<Double> tDouble = new Triple<Double>(da, db, dc);
		System.out.printf("%.2f\n", tDouble.max());
		System.out.printf("%.2f\n", tDouble.avarage());
		tDouble.sort();
		System.out.println(tDouble);
	}
}
```

### Solution
```java
import java.util.stream.Collectors;
import java.util.List;
import java.util.Scanner;

public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.avarage());
        tInt.sort();
        System.out.println(tInt);
        float fa = scanner.nextFloat();
        float fb = scanner.nextFloat();
        float fc = scanner.nextFloat();
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.avarage());
        tFloat.sort();
        System.out.println(tFloat);
        double da = scanner.nextDouble();
        double db = scanner.nextDouble();
        double dc = scanner.nextDouble();
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.avarage());
        tDouble.sort();
        System.out.println(tDouble);
    }
}



class Triple<T extends Number> {
    private List<T> numbers;

    public Triple(T a, T b, T c) {
        numbers = List.of(a, b, c);
    }

    public double max() {
        return numbers.stream().mapToDouble(Number::doubleValue).max().orElse(0);
    }

    public double avarage() {
        return numbers.stream().mapToDouble(Number::doubleValue).average().orElse(0);
    }

    public void sort() {
        numbers = numbers.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f"
                , numbers.get(0).doubleValue()
                , numbers.get(1).doubleValue()
                , numbers.get(2).doubleValue());
    }
}
```

### Note:
This task is not intended to be solved like this. It is intended to store each of the variables `T a`, `T b` and `T c` and then perform the calculations.