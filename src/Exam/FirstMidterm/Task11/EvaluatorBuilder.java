package Exam.FirstMidterm.Task11;

public class EvaluatorBuilder {
    public static <T extends Comparable<T>> IEvaluator<T> build(String operator) {
        switch (operator) {
            case ">":
                return (a, b) -> a.compareTo(b) > 0;
            case "==":
                return (a, b) -> a.compareTo(b) == 0;
            case "!=":
                return (a, b) -> a.compareTo(b) != 0;
            case "<":
                return (a, b) -> a.compareTo(b) < 0;
            default:
                throw new RuntimeException();
        }
    }
}
