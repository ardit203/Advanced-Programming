package Lab.Lab3.Task3;

import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private int id;
    private String name;
    private double gpa;
    private List<SubjectWithGrade> subjectWithGrades;
    private StudyProgramme studyProgramme;

    public Applicant(int id, String name, double gpa, StudyProgramme studyProgramme) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.subjectWithGrades = new ArrayList<>();
        this.studyProgramme = studyProgramme;
    }


    public void addSubjectAndGrade(String subject, int grade) {
        subjectWithGrades.add(new SubjectWithGrade(subject, grade));
    }

    public double calculatePoints() {
        double points = gpa * 12;
        for (SubjectWithGrade subjectWithGrade : subjectWithGrades) {
            if (studyProgramme.getFaculty().getAppropriateSubjects().contains(subjectWithGrade.getSubject())) {
                points += subjectWithGrade.getGrade() * 2;
            } else {
                points += subjectWithGrade.getGrade() * 1.2;
            }
        }
        return points;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s, GPA: %.1f - %f", id, name, gpa, calculatePoints());
    }
}
