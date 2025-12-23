package Exam.FirstMidterm.Task10;

import java.util.List;
import java.util.stream.Collectors;

public class Triple<T extends Number> {
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
