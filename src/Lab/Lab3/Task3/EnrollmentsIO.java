package Lab.Lab3.Task3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class EnrollmentsIO {
    public static void printRanked(List<Faculty> faculties) {
        Comparator<Faculty> facultyComparator = Comparator.comparingInt(f -> f.getStudyProgrammes().size());

        Comparator<StudyProgramme> studyProgrammeComparator = Comparator.comparingDouble(s -> {
            int numerator = s.getEnrolledInPublicQuota() + s.getEnrolledInPrivateQuota();
            int denominator = s.getNumPublicQuota() + s.getNumPrivateQuota();
            return denominator == 0 ? 0 : 100.0 * numerator / denominator;
        });

        Comparator<Applicant> applicantComparator = Comparator.comparingDouble(Applicant::calculatePoints);

    }

    public static void readEnrollments(List<StudyProgramme> studyProgrammes, InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        br.lines()
                .filter(Objects::nonNull)
                .forEach(l -> {
                    String[] tokens = l.split(";");
                    int id = Integer.parseInt(tokens[0]);
                    String name = tokens[1];
                    double gpa = Double.parseDouble(tokens[2]);
                    String code = tokens[tokens.length - 1];

                    StudyProgramme studyProgramme = studyProgrammes.stream()
                            .filter(s -> s.getCode().equals(code))
                            .findFirst()
                            .orElse(null);

                    Applicant applicant = new Applicant(id, name, gpa, studyProgramme);

                    for (int i = 3; i < tokens.length - 2; i+=2) {
                        applicant.addSubjectAndGrade(tokens[i], Integer.parseInt(tokens[i+1]));
                    }

                    studyProgramme.addApplicant(applicant);

                });
    }
}