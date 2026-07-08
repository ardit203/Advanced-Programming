package Exam.SecondMidtermExam.Task50;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Course implements Comparable<Course> {
    private String courseName;
    private List<Integer> grades;

    public Course(String courseName) {
        this.courseName = courseName;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double avgGrade() {
        return grades.stream().mapToInt(i -> i).average().orElse(5.0);
    }

    public int getCountOfStudents() {
        return grades.size();
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public int compareTo(Course o) {
        return Comparator.comparing(Course::getCountOfStudents)
                .thenComparing(Course::avgGrade)
                .thenComparing(Course::getCourseName)
                .compare(this, o);
    }

    @Override
    public String toString() {
        //course18 5 6.00↩
        return String.format("%s %d %.2f", courseName, getCountOfStudents(), avgGrade());
    }
}