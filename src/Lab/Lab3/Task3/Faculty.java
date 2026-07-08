package Lab.Lab3.Task3;

import java.util.ArrayList;
import java.util.List;

class Faculty {
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

    public List<String> getAppropriateSubjects() {
        return appropriateSubjects;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        Faculty: FINKI
//        Subjects: [Mother Tongue, Mathematics, Informatics]
//        Study Programmes:
        sb.append("Faculty: ").append(shortName).append("\n");
        sb.append("Subjects: ").append(appropriateSubjects).append("\n");
        sb.append("Study Programmes:\n");
        studyProgrammes.stream().sorted()
                .forEach(sb::append);
        return sb.toString();
    }
}
