# Lab Exercise 4 - Advanced Programming

## Task 2

**Your task is to implement the classes `Student` and `Faculty` according to the following specifications:**


* **Class `Student`:**

    * The constructor `Student(String id, List<Integer> grades)` initializes a student with the given ID and list of grades.
    * The `toString()` method returns the textual representation of the student in the format
      `Student{id='ID', grades=[grades]}`.


* **Class `Faculty`:**

    * The method `addStudent(String id, List<Integer> grades)` adds a new student to the collection with the given ID and list of grades.
      If a student with the same ID already exists, throw an exception with the message:
      `"Student with ID <id> already exists"`.
      **This method must have complexity `O(1)`.**

    * The method `addGrade(String id, int grade)` adds a new grade to an already existing student with the given ID.
      **This method must have complexity `O(1)`.**

    * The method `getStudentsSortedByAverageGrade()` returns a `Set<Student>` of students sorted in descending order based on average grade.
      If two students have the same average grade, they are sorted by number of passed courses, and if still tied, by ID in descending order.

    * The method `getStudentsSortedByCoursesPassed()` returns a `Set<Student>` of students sorted in descending order based on number of passed courses.
      If two students have the same number of passed courses, they are sorted by average grade, and then by ID in descending order.


 **Additional requirement for Friday, 13th:**

* Extend the `toString` method so it also prints the student's highest grade.
  Example: instead of
  `Student{id='S001', grades=[6, 7, 8, 9, 10]}`
  it should print
  `Student{id='S001', grades=[6, 7, 8, 9, 10], maxGrade=10}`

* Implement the method `getStudentsSortedByMaxGrade()` which returns a `Set<Student>` sorted in descending order by the highest grade of the student, and then by index.

### Starter code:
```java
import java.util.*;
import java.util.function.Function;


public class SetsTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Faculty faculty = new Faculty();

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            switch (command) {
                case "addStudent":
                    String id = tokens[1];
                    List<Integer> grades = new ArrayList<>();
                    for (int i = 2; i < tokens.length; i++) {
                        grades.add(Integer.parseInt(tokens[i]));
                    }
                    try {
                        faculty.addStudent(id, grades);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "addGrade":
                    String studentId = tokens[1];
                    int grade = Integer.parseInt(tokens[2]);
                    faculty.addGrade(studentId, grade);
                    break;

                case "getStudentsSortedByAverageGrade":
                    System.out.println("Sorting students by average grade");
                    Set<Student> sortedByAverage = faculty.getStudentsSortedByAverageGrade();
                    for (Student student : sortedByAverage) {
                        System.out.println(student);
                    }
                    break;

                case "getStudentsSortedByCoursesPassed":
                    System.out.println("Sorting students by courses passed");
                    Set<Student> sortedByCourses = faculty.getStudentsSortedByCoursesPassed();
                    for (Student student : sortedByCourses) {
                        System.out.println(student);
                    }
                    break;

                default:
                    break;
            }
        }

        scanner.close();
    }
}
```


### Solution:
```java
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SetsTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Faculty faculty = new Faculty();

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            switch (command) {
                case "addStudent":
                    String id = tokens[1];
                    List<Integer> grades = new ArrayList<>();
                    for (int i = 2; i < tokens.length; i++) {
                        grades.add(Integer.parseInt(tokens[i]));
                    }
                    try {
                        faculty.addStudent(id, grades);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "addGrade":
                    String studentId = tokens[1];
                    int grade = Integer.parseInt(tokens[2]);
                    faculty.addGrade(studentId, grade);
                    break;

                case "getStudentsSortedByAverageGrade":
                    System.out.println("Sorting students by average grade");
                    Set<Student> sortedByAverage = faculty.getStudentsSortedByAverageGrade();
                    for (Student student : sortedByAverage) {
                        System.out.println(student);
                    }
                    break;

                case "getStudentsSortedByCoursesPassed":
                    System.out.println("Sorting students by courses passed");
                    Set<Student> sortedByCourses = faculty.getStudentsSortedByCoursesPassed();
                    for (Student student : sortedByCourses) {
                        System.out.println(student);
                    }
                    break;

                default:
                    break;
            }
        }

        scanner.close();
    }
}


class Faculty {
    Map<String, Student> students;

    public Faculty() {
        this.students = new HashMap<>();
    }

    public void addStudent(String id, List<Integer> grades) throws Exception {
        Student student = students.putIfAbsent(id, new Student(id, grades));

        if (student != null) {
            throw new Exception(String.format("Student with ID %s already exists", id));
        }
    }

    public void addGrade(String studentId, int grade) {
        students.get(studentId).addGrade(grade);
    }

    public Set<Student> getStudentsSortedByAverageGrade() {
        Comparator<Student> comparator = Comparator.comparing(Student::averageGrade).reversed()
                .thenComparing(Comparator.comparing(Student::passedCourses).reversed())
                .thenComparing(Comparator.comparing(Student::getId).reversed());


        return students.values()
                .stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public Set<Student> getStudentsSortedByCoursesPassed() {
        Comparator<Student> comparator = Comparator.comparing(Student::passedCourses).reversed()
                .thenComparing(Comparator.comparing(Student::averageGrade).reversed())
                .thenComparing(Comparator.comparing(Student::getId).reversed());

        return students.values()
                .stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }
    
}


class Student {
    private String id;
    private List<Integer> grades;

    public Student(String id, List<Integer> grades) {
        this.id = id;
        this.grades = grades;
    }

    public void addGrade(int grade){
        grades.add(grade);
    }

    public double averageGrade(){
        return grades.stream().mapToInt(g -> g).average().orElse(0);
    }

    public int passedCourses(){
        return (int) grades.stream().filter(g -> g > 5).count();
    }

    public String getId(){
        return id;
    }

    @Override
    public String toString() {
        return String.format("Student{id='%s', grades=%s}", id, grades.toString());
    }
}
```

