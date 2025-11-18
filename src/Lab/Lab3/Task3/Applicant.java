package Lab.Lab3.Task3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Applicant implements Comparable<Applicant> {
    private int id;
    private String name;
    private double gpa;
    private List<SubjectWithGrade> subjectWithGrades;
    private StudyProgramme studyProgramme;


    public Applicant(int id, String name, double gpa, StudyProgramme studyProgramme) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.studyProgramme = studyProgramme;
        this.subjectWithGrades = new ArrayList<>();
    }

    public void addSubjectAndGrade(String subject, int grade) {
        subjectWithGrades.add(new SubjectWithGrade(subject, grade));
    }

    public double calculatePoints() {
        double sum = 0;
        List<String> appropriateSubjects = studyProgramme.getFaculty().getAppropriateSubjects();

        for (SubjectWithGrade swg : subjectWithGrades) {
            double factor = appropriateSubjects.contains(swg.getSubject()) ? 2 : 1.2;
            sum += swg.getGrade() * factor;
        }
        return sum += gpa * 12;
    }


    public int appropriateCount(List<String> appropriateSubjects) {
        return (int) subjectWithGrades.stream()
                .filter(swg -> appropriateSubjects.contains(swg.getSubject()))
                .count();
    }


    @Override
    public int compareTo(Applicant other) {
        return Comparator.comparingDouble(Applicant::calculatePoints).reversed()
                .thenComparing(Applicant::getId).compare(this, other);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name + ", GPA: " + gpa + " - " + calculatePoints() + "\n";
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}