package Lab.Lab3.Task3;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void addStudyProgramme(StudyProgramme studyProgramme) {
        studyProgrammes.add(studyProgramme);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Faculty: ").append(shortName).append("\n");
        sb.append("Subjects: ");
        sb.append(Arrays.toString(appropriateSubjects.toArray()));
        sb.append("\n");

        studyProgrammes = studyProgrammes.stream().sorted().collect(Collectors.toList());
        sb.append("Study Programmes:\n");

        studyProgrammes.forEach(sb::append);

        return sb.toString();
    }


    public List<String> getAppropriateSubjects() {
        return appropriateSubjects;
    }
}
