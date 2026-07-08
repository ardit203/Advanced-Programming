You need to write the class **`Student`** in which the following information will be stored:

* index of a FINKI student (a string consisting of six digits)
* a list of points earned on laboratory exercises for some FINKI course. The course has a maximum of 10 laboratory exercises.

For the class, write the constructor
**`Student(String index, List<Integer> points)`**.

---

You need to write a class **`LabExercises`** in which a collection of students is stored. For the class, write the following methods:

* **`public void addStudent (Student student)`** – method for adding a new student to the collection
* **`public void printByAveragePoints (boolean ascending, int n)`** – method that prints the first `n` students sorted by their total points, and if the total points are equal, by index; in ascending order if `ascending` is true, otherwise in descending order.
  The total points are calculated as the sum of the points divided by 10.
* **`public List<Student> failedStudents ()`** – method that returns a list of students who did not receive a signature (they have more than 2 absences), sorted first by index and then by total points.
* **`public Map<Integer,Double> getStatisticsByYear()`** – method that returns a map of the average total points of the students grouped by year of study. Students who did not receive a signature should be ignored.

### Starter code
```java
public class LabExercisesTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

    }
}
```

### Solution
```java
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String index;
    private List<Integer> points;
    private double totalPoints;

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
        this.totalPoints = points.stream().mapToInt(p -> p).sum() / 10.0;
    }

    public String getIndex() {
        return index;
    }

    public boolean hasSignature() {
        return points.size() >= 8;
    }

    public double totalPoints() {
        return totalPoints;
    }

    public int getYearOfStudy(){
        int year = 2000 + Integer.parseInt(index.substring(0,2));
        int now = 2020;
        return now - year;
    }


    @Override
    public String toString() {
        return String.format("%s %s %.2f", index, hasSignature() ? "YES" : "NO", totalPoints);
    }
}

class LabExercises {
    private List<Student> students;

    public LabExercises() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void printByAveragePoints(boolean ascending, int n) {
        Comparator<Student> cmp = Comparator.comparing(Student::totalPoints).thenComparing(Student::getIndex);
        if (!ascending) {
            cmp = cmp.reversed();
        }

        students.stream()
                .sorted(cmp)
                .limit(n)
                .forEach(System.out::println);
    }

    public List<Student> failedStudents() {
        Comparator<Student> cmp = Comparator.comparing(Student::getIndex).thenComparing(Student::totalPoints);
        return students.stream()
                .filter(s -> !s.hasSignature())
                .sorted(cmp)
                .collect(Collectors.toList());
    }

    public Map<Integer, Double> getStatisticsByYear() {
        return students.stream()
                .filter(Student::hasSignature)
                .collect(Collectors.groupingBy(
                        Student::getYearOfStudy,
                        TreeMap::new,
                        Collectors.averagingDouble(Student::totalPoints)
                ));
    }
}

public class LabExercisesTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabExercises labExercises = new LabExercises();
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] parts = input.split("\\s+");
            String index = parts[0];
            List<Integer> points = Arrays.stream(parts).skip(1)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            labExercises.addStudent(new Student(index, points));
        }

        System.out.println("===printByAveragePoints (ascending)===");
        labExercises.printByAveragePoints(true, 100);
        System.out.println("===printByAveragePoints (descending)===");
        labExercises.printByAveragePoints(false, 100);
        System.out.println("===failed students===");
        labExercises.failedStudents().forEach(System.out::println);
        System.out.println("===statistics by year");
        labExercises.getStatisticsByYear().entrySet().stream()
                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
                .forEach(System.out::println);

    }
}
```