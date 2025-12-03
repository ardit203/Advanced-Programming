package Lab.Lab4.Task2;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Student {
    private String id;
    private List<Integer> grades;

    public Student(String id, List<Integer> grades) {
        this.id = id;
        this.grades = grades;
    }

    public void addGrade(int grade){
        grades.add(grade);
    }

    public double averageGrade(){
        return grades.stream().mapToInt(g -> g).average().orElse(0);
    }

    public int passedCourses(){
        return (int) grades.stream().filter(g -> g > 5).count();
    }



    public String getId(){
        return id;
    }

    public List<Integer> getGrades(){
        return grades;
    }

    @Override
    public String toString() {
        return String.format("Student{id='%s', grades=%s}", id, grades.toString());
    }

    //Additional Requirement
    public int maxGrade(){
        return grades.stream().max(Comparator.comparing(Integer::intValue)).orElse(0);
    }

//    @Override
//    public String toString() {
//        return String.format("Student{id='%s', grades=%s, maxGrade=%d}", id, grades.toString(), maxGrade());
//    }

}
