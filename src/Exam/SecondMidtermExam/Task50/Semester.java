package Exam.SecondMidtermExam.Task50;

import java.util.Map;
import java.util.TreeMap;

public class Semester {
    private int term;
    private String studentId;
    private Map<String, Integer> courses;//holds the course and the grade in that course in the term

    public Semester(int term, String studentId) {
        this.term = term;
        this.studentId = studentId;
        this.courses = new TreeMap<>();
    }

    public void addGrade(String courseName, int grade, Map<String, Integer> allCourses) throws OperationNotAllowedException {
        if (courses.size() == 3) {
            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d", studentId, term));
        }
        courses.put(courseName, grade);//student can pass a course with grade 5
        allCourses.put(courseName, grade);//this keeps track of all the courses and grades of a student
    }

    public double avgGrade() {//calculating avgGrade for term
        return courses.values().stream().mapToInt(c -> c).average().orElse(5.0);
    }

    public static String print(int term, int numCourses, double avgGrade) {
        return String.format("Term %d\nCourses: %d\nAverage grade for term: %.2f", term, numCourses, avgGrade);
    }

    @Override
    public String toString() {
        return print(term, courses.size(), avgGrade());
    }

    public static String getDefaultPrinting(int term) {//This is used if a student is in term 5 for example,
        // and he hasn't enrolled in term 6 but the testCases require u to print all the terms even if no enrollment happened
        return print(term, 0, 5.0);
    }

}