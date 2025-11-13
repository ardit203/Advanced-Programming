package Lab.Lab3.Task3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Faculty {
    private String shortName;
    private List<String> appropriateSubjects;
    private List<StudyProgramme> studyProgrammes;

    public Faculty(String shortName) {
        this.shortName = shortName;
        this.appropriateSubjects = new ArrayList<>();
        this.studyProgrammes = new ArrayList<>();
    }

    public void addSubject(String subject) {
        appropriateSubjects.add(subject);
    }

    public List<String> getAppropriateSubjects(){
        return appropriateSubjects;
    }

    public void addStudyProgramme(StudyProgramme studyProgramme) {
        studyProgrammes.add(studyProgramme);
    }

    public List<StudyProgramme> getStudyProgrammes(){
        return studyProgrammes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Faculty: ").append(shortName).append("\n");
        sb.append("Subjects: ").append(appropriateSubjects).append("\n");
        sb.append("Study Programmes: \n");



        return sb.toString();
    }
}
