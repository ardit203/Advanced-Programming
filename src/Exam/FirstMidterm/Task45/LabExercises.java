package Exam.FirstMidterm.Task45;

import java.util.*;
import java.util.stream.Collectors;

public class LabExercises {
    private List<Student> students;


    public LabExercises(){
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }


    public void printByAveragePoints(boolean ascending, int n) {
        List<Student> studentList;
        if (ascending) {
            studentList = students.stream()
                    .sorted(Comparator.comparingDouble(Student::getTotalPoints).thenComparing(Student::getIndex))
                    .collect(Collectors.toList());
        }else {
            studentList = students.stream()
                    .sorted(Comparator.comparingDouble(Student::getTotalPoints).thenComparing(Student::getIndex).reversed())
                    .collect(Collectors.toList());
        }

        studentList.stream().limit(n).forEach(System.out::println);
    }


    public List<Student> failedStudents (){
        return students.stream()
                .filter(Student::hasNoSignature)
                .sorted(Comparator.comparing(Student::getIndex).thenComparing(Student::getTotalPoints)).collect(Collectors.toList());
    }

    public Map<Integer,Double> getStatisticsByYear(){
        return students.stream().filter(Student::hasSignature).collect(Collectors.groupingBy(
                Student::getYear,
                TreeMap::new,
                Collectors.averagingDouble(Student::getTotalPoints)
        ));
    }
}
