package Lab.Lab3.Task3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class EnrollmentsIO {
    public static void printRanked(List<Faculty> faculties) {
        faculties.forEach(System.out::print);
    }

    public static void readEnrollments(List<StudyProgramme> studyProgrammes, InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        br.lines().filter(Objects::nonNull)
                .forEach(l -> {
                    String[] tokens = l.split(";");
                    int id = Integer.parseInt(tokens[0]);
                    String name = tokens[1];
                    double gpa = Double.parseDouble(tokens[2]);
                    String studyProgrammeCode = tokens[tokens.length - 1];

                    StudyProgramme studyProgramme = studyProgrammes.stream()
                            .filter(s -> s.getCode().equals(studyProgrammeCode))
                            .findFirst()
                            .orElse(null);

                    if (studyProgramme != null) {
                        Applicant applicant = new Applicant(id, name, gpa, studyProgramme);

                        for (int i = 3; i < tokens.length - 2; i += 2) {
                            String subject = tokens[i];
                            int grade = Integer.parseInt(tokens[i + 1]);
                            applicant.addSubjectAndGrade(subject, grade);
                        }

                        studyProgramme.addApplicant(applicant);
                    }
                });
    }
}