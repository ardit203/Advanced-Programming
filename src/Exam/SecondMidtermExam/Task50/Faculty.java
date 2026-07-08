package Exam.SecondMidtermExam.Task50;

import java.util.*;

public class Faculty {
    private Map<String, Student> students;//keeps track of all students by id
    private Map<String, Course> courses;//all the courses that students enrolled with id courseName,
    // and inside keeps the courseName, and all the grades the students got in the specific course
    private List<String> logs; //if a student graduates we add the graduation message to this list

    public Faculty() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        this.logs = new ArrayList<>();
    }

    void addStudent(String id, int yearsOfStudies) {
        students.putIfAbsent(id, new Student(id, yearsOfStudies));
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        Student student = students.get(studentId);
        student.addGradeToSemester(term, courseName, grade);
        courses.computeIfAbsent(courseName, k -> new Course(courseName)).addGrade(grade);
        if (student.hasGraduated()) {
            students.remove(studentId);
            logs.add(student.getGraduationMessage());
        }
    }

    public String getFacultyLogs() {
        return String.join("\n", logs);
    }

    String getDetailedReportForStudent(String id) {
        return students.get(id).getReport();
    }

    void printFirstNStudents(int n) {//prints the top students, because we are not allowed to use sort methods,
        // we add all students to a TreeSet which keeps the students sorted using the compareTo method inside Student
        new TreeSet<>(students.values())
                .stream()
                .limit(n)
                .forEach(System.out::println);
    }

    void printCourses() {//same ide as printFirstNStudents
        new TreeSet<>(courses.values())
                .forEach(System.out::println);
    }
}