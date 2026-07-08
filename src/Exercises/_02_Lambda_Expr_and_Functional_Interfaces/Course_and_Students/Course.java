package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Course_and_Students;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Course {
    private Student[] students;
    private int numStudents;
    private String title;

    public Course(String title, int maxStudents) {
        this.students = new Student[maxStudents];
        this.numStudents = 0;
        this.title = title;
    }

    public void enroll(Supplier<Student> supplier) {
        if (numStudents == students.length) {
            return;
        }
        students[numStudents++] = supplier.get();
    }

    public void forEach(Consumer<Student> action) {
        for (int i = 0; i < numStudents; i++) {
            action.accept(students[i]);
        }
    }

    public int count(Predicate<Student> condition) {
        int count = 0;
        for (int i = 0; i < numStudents; i++) {
            if (condition.test(students[i])) {
                count++;
            }
        }
        return count;
    }

    public Student findFirst(Predicate<Student> condition) {
        for (int i = 0; i < numStudents; i++) {
            if (condition.test(students[i])) {
                return students[i];
            }
        }
        return null;
    }

    public Student[] filter(Predicate<Student> condition) {
        Student[] s = new Student[count(condition)];
        int j = 0;
        for (int i = 0; i < numStudents; i++) {
            if (condition.test(students[i])) {
                s[j++] = students[i];
            }
        }
        return s;
    }

    public String[] mapToLabels(Function<Student, String> mapper) {
        String[] labels = new String[numStudents];
        for (int i = 0; i < numStudents; i++) {
            labels[i] = mapper.apply(students[i]);
        }
        return labels;
    }

    public void mutate(Consumer<Student> mutator) {
        for (int i = 0; i < numStudents; i++) {
            mutator.accept(students[i]);
        }
    }

    public void conditionalMutate(Predicate<Student> condition, Consumer<Student> mutator) {
        for (int i = 0; i < numStudents; i++) {
            if (condition.test(students[i])) {
                mutator.accept(students[i]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course: ").append(title).append("\n\n");
        for (int i = 0; i < numStudents; i++) {
            sb.append(students[i]).append('\n');
        }
        return sb.toString();
    }
}
