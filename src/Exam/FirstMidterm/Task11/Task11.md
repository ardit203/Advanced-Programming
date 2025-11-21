Define a generic interface `IEvaluator` which will have only one method:

* `boolean evaluate (T a, T b)` – a method that returns true/false for some type of comparison between two objects of the same class that are **comparable**.

Define a class `EvaluatorBuilder` which will have only one generic static method:

* `static IEvaluator build (String operator)` – a method that returns an object that implements the interface `IEvaluator`.
  The implementation of these objects must be based on the operator given as an argument to the function.
  The operator can be:

    * `>`
    * `>=`
    * `==`
    * `!=`
    * `<`

* the implementations of the interface must be provided using **lambda expressions**!

Define a class `Evaluator` which will have only one generic static method:

* `static boolean evaluateExpression (T left, T right, String operator)` – a method that takes three arguments: the first two are the values to be evaluated, and the third is the operator according to which the evaluation will be performed.
  In this method, the appropriate evaluator must be created based on the operator, and the two values `left` and `right` must be evaluated.


### Starter code
```java
public class EvaluatorTest {

    private static class Student implements Comparable<Student>{
        String id;
        Double average;
        int year;

        Student(String id, Double average, int year) {
            this.id = id;
            this.average = average;
            this.year = year;
        }

        public static Student createInstance (String input) {
            String [] parts = input.split("\\s+");
            Double average = Double.parseDouble(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return new Student(parts[0], average, year);
        }

        @Override
        public int compareTo(Student student) {
            int compResult = Double.compare(this.average, student.average);
            if (compResult==0)
                return Integer.compare(this.year, student.year);
            else
                return compResult;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String [] parts = line.split("\\s+");
            String operator = parts[2];

            if (parts[0].equals("1")) { //Integers
                Integer left = Integer.valueOf(parts[1]);
                Integer right = Integer.valueOf(parts[3]);
                System.out.println(Evaluator.evaluateExpression(left,right,operator));

            }
            else if (parts[0].equals("2")) { //Double
                Double left = Double.valueOf(parts[1]);
                Double right = Double.valueOf(parts[3]);
                System.out.println(Evaluator.evaluateExpression(left,right,operator));
            }
            else if (parts[0].equals("3")) { //Characters
                Character left = parts[1].charAt(0);
                Character right = parts[3].charAt(0);
                System.out.println(Evaluator.evaluateExpression(left,right,operator));
            }
            else if (parts[0].equals("4")) { //String
                System.out.println(Evaluator.evaluateExpression(parts[1],parts[3],operator));
            }
            else { // Students
                operator = parts[3];
                String sInfo1 = Arrays.stream(parts).limit(3).collect(Collectors.joining(" "));
                String sInfo2 = Arrays.stream(parts).skip(4).limit(3).collect(Collectors.joining(" "));
                Student s1 = Student.createInstance(sInfo1);
                Student s2 = Student.createInstance(sInfo2);
                System.out.println(Evaluator.evaluateExpression(s1,s2,operator));
            }
        }

    }
}
```

### Solution
```java
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

interface IEvaluator<T extends Comparable<T>> {
    boolean evaluate(T a, T b);
}

@SuppressWarnings("unchecked")
class EvaluatorBuilder {
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


@SuppressWarnings("unchecked")
class Evaluator {
    public  static <T extends Comparable<T>> boolean evaluateExpression (T left, T right, String operator){
        return EvaluatorBuilder.build(operator).evaluate(left, right);
    }
}

public class EvaluatorTest {

    private static class Student implements Comparable<Student>{
        String id;
        Double average;
        int year;

        Student(String id, Double average, int year) {
            this.id = id;
            this.average = average;
            this.year = year;
        }

        public static Student createInstance (String input) {
            String [] parts = input.split("\\s+");
            Double average = Double.parseDouble(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return new Student(parts[0], average, year);
        }

        @Override
        public int compareTo(Student student) {
            int compResult = Double.compare(this.average, student.average);
            if (compResult==0)
                return Integer.compare(this.year, student.year);
            else
                return compResult;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String [] parts = line.split("\\s+");
            String operator = parts[2];

            if (parts[0].equals("1")) { //Integers
                Integer left = Integer.valueOf(parts[1]);
                Integer right = Integer.valueOf(parts[3]);
                System.out.println(Evaluator.evaluateExpression(left,right,operator));

            }
            else if (parts[0].equals("2")) { //Double
                Double left = Double.valueOf(parts[1]);
                Double right = Double.valueOf(parts[3]);
                System.out.println(Evaluator.evaluateExpression(left,right,operator));
            }
            else if (parts[0].equals("3")) { //Characters
                Character left = parts[1].charAt(0);
                Character right = parts[3].charAt(0);
                System.out.println(Evaluator.evaluateExpression(left,right,operator));
            }
            else if (parts[0].equals("4")) { //String
                System.out.println(Evaluator.evaluateExpression(parts[1],parts[3],operator));
            }
            else { // Students
                operator = parts[3];
                String sInfo1 = Arrays.stream(parts).limit(3).collect(Collectors.joining(" "));
                String sInfo2 = Arrays.stream(parts).skip(4).limit(3).collect(Collectors.joining(" "));
                Student s1 = Student.createInstance(sInfo1);
                Student s2 = Student.createInstance(sInfo2);
                System.out.println(Evaluator.evaluateExpression(s1,s2,operator));
            }
        }

    }
}
```