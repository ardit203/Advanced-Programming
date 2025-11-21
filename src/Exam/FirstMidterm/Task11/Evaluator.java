package Exam.FirstMidterm.Task11;

@SuppressWarnings("unchecked")
public class Evaluator {
    public  static <T extends Comparable<T>> boolean evaluateExpression (T left, T right, String operator){
        return EvaluatorBuilder.build(operator).evaluate(left, right);
    }
}
