package Lab.Lab3.Task3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Applicant implements Comparable<Applicant> {
    private int id;
    private String name;
    private double gpa;
    private List<SubjectWithGrade> subjectWithGrades;
    private StudyProgramme studyProgramme;

    public Applicant(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.subjectWithGrades = new ArrayList<>();
        this.studyProgramme = null;
    }

    public void setStudyProgramme(StudyProgramme studyProgramme) {
        this.studyProgramme = studyProgramme;
    }

    void addSubjectAndGrade(String subject, int grade) {
        subjectWithGrades.add(new SubjectWithGrade(subject, grade));
    }

    double calculatePoints() {
        double sum = 0.0;
        for (SubjectWithGrade subjectWithGrade : subjectWithGrades) {
            int grade = subjectWithGrade.getGrade();
            if (studyProgramme.getFaculty().getAppropriateSubjects().contains(subjectWithGrade.getSubject())) {
                sum += grade * 2.0;
                continue;
            }
            sum += grade * 1.2;
        }
        return sum += gpa * 12;
    }

    private int appropriateSubjects() {
        List<String> subjects = studyProgramme.getFaculty().getAppropriateSubjects();
        int count = 0;
        for (SubjectWithGrade subjectWithGrade : subjectWithGrades) {
            if (subjects.contains(subjectWithGrade.getSubject())) {
                count++;
            }
        }
        return count;
    }


    @Override
    public int compareTo(Applicant o) {
        return Comparator.comparing(Applicant::appropriateSubjects)
                .thenComparing(Applicant::calculatePoints, Comparator.reverseOrder())
                .compare(this, o);
    }

    @Override
    public String toString() {
        //Id: 2, Name: Student B, GPA: 4.0 - 88.0
        return "Id: " + id + ", Name: " + name + ", GPA: " + gpa + " - " + calculatePoints();
    }
}
