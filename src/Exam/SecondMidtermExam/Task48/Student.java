package Exam.SecondMidtermExam.Task48;

import java.util.List;

public class Student implements Comparable<Student> {
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