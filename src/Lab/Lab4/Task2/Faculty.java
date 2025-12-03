package Lab.Lab4.Task2;

import java.util.*;
import java.util.stream.Collectors;

public class Faculty {
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

    //Additional Requirement
    public Set<Student> getStudentsSortedByMaxGrade(){
        Comparator<Student> comparator = Comparator.comparing(Student::maxGrade).reversed().thenComparing(Student::getId);
        return students.values()
                .stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    public Set<Student> getTopNStudents(int n){
        Comparator<Student> comparator = Comparator.comparing(Student::maxGrade).reversed();
        return students.values()
                .stream()
                .sorted(comparator)
                .limit(n)
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)));
    }

    Map<Integer, Integer> getGradeDistribution(){
        Map<Integer, Integer> result = new TreeMap<>(Comparator.reverseOrder());

        for (int i = 5; i <11 ; i++) {
            int grade = i;
            int count = (int) students.values()
                    .stream()
                    .flatMap(s -> s.getGrades().stream())
                    .filter(g -> g == grade)
                    .count();
            result.put(grade, count);
        }

        return result;
    }

    public void printDistribution(){
        Map<Integer, Integer> distribution = getGradeDistribution();

        StringBuilder sb = new StringBuilder();

        for (int i = 5; i < 11; i++) {
            String s = "-".repeat(i);
            sb.append(i).append(":\t").append(s).append("\t").append(distribution.get(i)).append("\n");
        }
        System.out.print(sb);
    }

}
