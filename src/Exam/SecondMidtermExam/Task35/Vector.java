package Exam.SecondMidtermExam.Task35;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vector {
    public static final Vector DEFAULT = new Vector(List.of(5, 5, 5, 5, 5));
    public static final Vector IDENTITY = new Vector(List.of(0, 0, 0, 0, 0));

    List<Integer> vector;

    public Vector(List<Integer> vector) {
        this.vector = vector;
    }

    public Vector sum(Vector other) {
        List<Integer> sum = IntStream.range(0, other.vector.size())
                .map(i -> vector.get(i) + other.vector.get(i))
                .boxed()
                .collect(Collectors.toList());

        return new Vector(sum);
    }

    public int max() {
        return vector.stream().mapToInt(v -> v).max().orElse(0);
    }
}