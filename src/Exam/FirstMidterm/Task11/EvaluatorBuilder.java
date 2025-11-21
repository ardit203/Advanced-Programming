package Exam.FirstMidterm.Task11;

interface IEvaluator<T extends Comparable<T>> {
    boolean evaluate(T a, T b);
}

@SuppressWarnings("unchecked")
public class EvaluatorBuilder {
    public static IEvaluator build(String operator) {
        if (operator.equals(">")) {
            return (a, b) -> a.compareTo(b) > 0;
        } else if (operator.equals(">=")) {
            return (a, b) -> a.compareTo(b) >= 0;
        } else if (operator.equals("==")) {
            return (a, b) -> a.compareTo(b) == 0;
        } else if (operator.equals("!=")) {
            return (a, b) -> a.compareTo(b) != 0;
        } else if (operator.equals("<")) {
            return (a, b) -> a.compareTo(b) < 0;
        }
        return (a, b) -> a.compareTo(b) <= 0;
    }
}
