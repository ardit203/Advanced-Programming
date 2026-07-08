package Lab.Lab3.Task3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

class EnrollmentsIO {
    public static void printRanked(List<Faculty> faculties) {
        faculties.forEach(System.out::print);
    }

    public static void readEnrollments(List<StudyProgramme> studyProgrammes, InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        br.lines()
                .forEach(line -> {
                    String[] tokens = line.split(";");
                    int id = Integer.parseInt(tokens[0]);
                    String name = tokens[1];
                    double gpa = Double.parseDouble(tokens[2]);

                    Applicant applicant = new Applicant(id, name, gpa);
                    for (int i = 3; i < tokens.length - 2; i += 2) {
                        String subject = tokens[i];
                        int grade = Integer.parseInt(tokens[i + 1]);
                        applicant.addSubjectAndGrade(subject, grade);
                    }
                    String code = tokens[tokens.length - 1];
                    StudyProgramme studyProgramme = findStudyProgramme(code, studyProgrammes);
                    applicant.setStudyProgramme(studyProgramme);
                    studyProgramme.addApplicant(applicant);
                });
    }

    private static StudyProgramme findStudyProgramme(String code, List<StudyProgramme> studyProgrammes) {
        return studyProgrammes.stream().filter(s -> s.getCode().equals(code)).findFirst().get();
    }
}
