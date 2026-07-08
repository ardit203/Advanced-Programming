Implement a class `StudentRecords` which will read from an input stream (standard input, file, ...) data for student records.
The data contains: code (unique string), program/major (string of 3 letters), and an array of grades (integers from 6–10).
All data is separated by a single space. Example of the data format:

```text
ioqmx7 MT 10 8 10 8 10 7 6 9 9 9 6 8 6 6 9 9 8
```

Your task is to implement the following methods:

* `StudentRecords()` – default constructor
* `int readRecords(InputStream inputStream)` – method for reading the data which returns the total number of records read
* `void writeTable(OutputStream outputStream)` – method that prints all records for all students grouped by program (first the name of the program is printed), and then all records for the students from that program are printed, sorted by average grade in descending order (if they have the same average, they are sorted lexicographically by code) in the format `kod prosek`, where the average is a decimal number rounded to two decimals. Example: `jeovz8 8.47`. The programs are sorted lexicographically. The complexity of the method must not exceed $O(N)$ with respect to the number of records.
* `void writeDistribution(OutputStream outputStream)` – method for printing the distribution of the number of grades per program, where the programs are sorted by the number of tens (grade 10) in ascending order (first is the program with the highest number of grade ten). The distribution of grades is printed in the following format:

    ```text
    PROGRAM
    [grade right-aligned in a field of width 2] | [one * character for every 10 grades] ([total grades])
    ```

    Example:

    ```text
    KNI
     6 | ***********(103)
     7 | ******************(173)
     8 | *******************(184)
     9 | *****************(161)
    10 | **************(138)
    ```

The complexity of this method must not exceed $O(N * M * log_2(M))$ for N records and M programs.


### Starter code
```java
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * January 2016 Exam problem 1
 */
public class StudentRecordsTest {
  public static void main(String[] args) {
    System.out.println("=== READING RECORDS ===");
    StudentRecords studentRecords = new StudentRecords();
    int total = studentRecords.readRecords(System.in);
    System.out.printf("Total records: %d\n", total);
    System.out.println("=== WRITING TABLE ===");
    studentRecords.writeTable(System.out);
    System.out.println("=== WRITING DISTRIBUTION ===");
    studentRecords.writeDistribution(System.out);
  }
}
```

### Solution
```java
// package Exam.SecondMidtermExam.Task48;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * January 2016 Exam problem 1
 */


class Student implements Comparable<Student> {
    private String code;
    private String program;
    private List<Integer> grades;
    private double avgGrade;

    public Student(String code, String program, List<Integer> grades) {
        this.code = code;
        this.program = program;
        this.grades = grades;
        this.avgGrade = grades.stream().mapToInt(i -> i).average().orElse(0);
    }

    public String getCode() {
        return code;
    }

    public String getProgram() {
        return program;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    @Override
    public int compareTo(Student o) {
        int compare1 = Double.compare(o.avgGrade, this.avgGrade);
        if (compare1 == 0) return this.code.compareTo(o.code);
        return compare1;
    }

    @Override
    public String toString() {
//        ookrq3 8.86
        return String.format("%s %.2f", code, avgGrade);
    }
}

class StudentFactory {
    public static Student createStudent(String line) {
        String[] tokens = line.split("\\s++");
        String code = tokens[0];
        String program = tokens[1];

        List<Integer> grades = new ArrayList<>();
        for (int i = 2; i < tokens.length; i++) {
            grades.add(Integer.parseInt(tokens[i]));
        }
        return new Student(code, program, grades);
    }
}

class Distribution implements Comparable<Distribution> {
    String program;
    Map<Integer, Integer> distributions;

    public Distribution(Map.Entry<String, Set<Student>> entry) {
        this.program = entry.getKey();
        this.distributions = new TreeMap<>();
        mapper(entry.getValue());
    }

    public void mapper(Set<Student> students) {
        for (int i = 6; i <= 10; i++) {
            int index = i;
            int count = (int) students
                    .stream()
                    .flatMap(s -> s.getGrades().stream()).filter(g -> g == index)
                    .count();
            distributions.put(i, count);
        }
    }

    @Override
    public int compareTo(Distribution o) {
        return Integer.compare(o.distributions.get(10), distributions.get(10));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(program).append("\n");
        distributions.forEach((k, v) -> {
            int c = (int) Math.ceil(v / 10.0);
            String astr = "*".repeat(c);
            sb.append(String.format("%2d | %s(%d)\n", k, astr, v));
        });
        return sb.toString();
    }
}


class StudentRecords {
    private Map<String, Set<Student>> students;

    public StudentRecords() {
        this.students = new TreeMap<>();
    }

    public int readRecords(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            Student student = StudentFactory.createStudent(line);
            students.computeIfAbsent(student.getProgram(), k -> new TreeSet<>()).add(student);
            count++;
        }
        return count;
    }

    public void writeTable(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        students.forEach((k, v) -> {
            pw.println(k);
            v.forEach(pw::println);
        });
        pw.flush();
    }

    public void writeDistribution(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        students.entrySet()
                .stream()
                .map(Distribution::new)
                .sorted()
                .forEach(pw::print);
        pw.flush();
    }
}


public class StudentRecordsTest {
    public static void main(String[] args) throws IOException {
        System.out.println("=== READING RECORDS ===");
        StudentRecords studentRecords = new StudentRecords();
        int total = studentRecords.readRecords(System.in);
        System.out.printf("Total records: %d\n", total);
        System.out.println("=== WRITING TABLE ===");
        studentRecords.writeTable(System.out);
        System.out.println("=== WRITING DISTRIBUTION ===");
        studentRecords.writeDistribution(System.out);
    }
}
```