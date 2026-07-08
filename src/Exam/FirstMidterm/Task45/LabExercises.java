package Exam.FirstMidterm.Task45;

import java.util.*;
import java.util.stream.Collectors;

public class LabExercises {
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