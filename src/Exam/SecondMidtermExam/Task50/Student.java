package Exam.SecondMidtermExam.Task50;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Student implements Comparable<Student> {
    private String id;
    private int yearsOfStudies;
    private Map<Integer, Semester> semesters;//keeps the term as the key and the term(Semester) as value
    private Map<String, Integer> courses;//keeps track of all the courses and grades of a student

    public Student(String id, int yearsOfStudies) {
        this.id = id;
        this.yearsOfStudies = yearsOfStudies;
        this.semesters = new TreeMap<>();
        this.courses = new TreeMap<>();
    }

    public void addGradeToSemester(int term, String courseName, int grade) throws OperationNotAllowedException {
        checkTermCondition(term);
        semesters.computeIfAbsent(term, k -> new Semester(term, id)).addGrade(courseName, grade, courses);
    }

    private void checkTermCondition(int term) throws OperationNotAllowedException {
        boolean threeYearCondition = (yearsOfStudies == 3 && term > 6);
        boolean fourYearCondition = (yearsOfStudies == 4 && term > 8);

        if (threeYearCondition || fourYearCondition) {
            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s", term, id));
        }
    }

    public int passedCourses() {//returns the number of passed courses, a course is passed even with grade 5
        return courses.size();
    }

    public boolean hasGraduated() {//checks if a student has graduated, the student can have 5's yet still graduate
        int numOfPassedCourses = yearsOfStudies == 3 ? 18 : 24;
        return courses.size() == numOfPassedCourses;
    }

    public double avgGrade() {//avgGrad of a student
        return courses.values().stream().mapToInt(c -> c).average().orElse(5.0);
    }

    public String getReport() {//prints the report for a student
        StringBuilder sb = new StringBuilder();

        sb.append("Student: ").append(id).append("\n");
        int numSemesters = yearsOfStudies == 3 ? 6 : 8;
        for (int i = 1; i < numSemesters + 1; i++) {
            Semester semester = semesters.get(i);
            if (semester == null) {
                sb.append(Semester.getDefaultPrinting(i)).append("\n");
                continue;
            }
            sb.append(semester).append("\n");
        }

        sb.append("Average grade: ").append(String.format("%.2f", avgGrade())).append("\n");
        sb.append("Courses attended: ").append(String.join(",", courses.keySet()));
        return sb.toString();
    }

    public String getGraduationMessage() {
        //Student with ID [studentID] graduated with average grade [averageGrade] in [yearsOfStudies] years
        return String.format("Student with ID %s graduated with average grade %.2f in %d years.", id, avgGrade(), yearsOfStudies);
    }

    @Override
    public String toString() {
        //Student: [id] Courses passed: [coursesPassed] Average grade: [averageGrade]
        return String.format("Student: %s Courses passed: %d Average grade: %.2f", id, passedCourses(), avgGrade());
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Student o) {
        return Comparator.comparing(Student::passedCourses)
                .thenComparing(Student::avgGrade)
                .thenComparing(Student::getId)
                .reversed()
                .compare(this, o);
    }
}