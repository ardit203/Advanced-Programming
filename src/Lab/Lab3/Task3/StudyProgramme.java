package Lab.Lab3.Task3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudyProgramme implements Comparable<StudyProgramme> {
    private String code;
    private String name;
    private Faculty faculty;
    private int numPublicQuota;
    private int numPrivateQuota;
    private int enrolledInPublicQuota;
    private int enrolledInPrivateQuota;
    private List<Applicant> applicants;

    public StudyProgramme(String code, String name, Faculty faculty, int numPublicQuota, int numPrivateQuota) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.numPublicQuota = numPublicQuota;
        this.numPrivateQuota = numPrivateQuota;
        this.enrolledInPublicQuota = 0;
        this.enrolledInPrivateQuota = 0;
        this.applicants = new ArrayList<>();
    }

    public void calculateEnrollmentNumbers() {
        int n = applicants.size();
        enrolledInPublicQuota = Math.min(n, numPublicQuota);
        int remaining = n - enrolledInPublicQuota;
        enrolledInPrivateQuota = Math.min(remaining, numPrivateQuota);
    }


    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }

    public int appropriateSubjects() {
        List<String> appropriateSubjects = faculty.getAppropriateSubjects();
        return applicants.stream()
                .mapToInt(a -> a.appropriateCount(appropriateSubjects))
                .distinct()
                .sum();
    }

    public double getPercentage() {
        return (enrolledInPublicQuota + enrolledInPrivateQuota) * 100.0 / (numPublicQuota + numPrivateQuota);
    }

    public double totalPoints() {
        return applicants.stream().mapToDouble(Applicant::calculatePoints).sum();
    }


    @Override
    public int compareTo(StudyProgramme other) {
        //In the text this is the comparison they're asking for, but it's FINKI
//        return Comparator.comparingInt(StudyProgramme::appropriateSubjects)
//                .thenComparing(Comparator.comparing(StudyProgramme::getPercentage).reversed())
//                .thenComparing(Comparator.comparing(StudyProgramme::totalPoints).reversed())
//                .compare(this, other);

        return Comparator.comparingDouble(StudyProgramme::getPercentage).reversed().compare(this, other);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
//        sb.append(String.format("Subs: %d --- Percent: %.2f --- Total: %.2f\n", appropriateSubjects(), getPercentage(), totalPoints()));

        applicants = applicants.stream().sorted().collect(Collectors.toList());

        List<Applicant> publicQuota = applicants.stream().limit(enrolledInPublicQuota).collect(Collectors.toList());

        List<Applicant> privateQuota = applicants.stream()
                .skip(enrolledInPublicQuota)
                .limit(enrolledInPrivateQuota)
                .collect(Collectors.toList());

        List<Applicant> rejected = applicants.stream()
                .skip(enrolledInPublicQuota + enrolledInPrivateQuota)
                .collect(Collectors.toList());

        sb.append("Public Quota:\n");

        publicQuota.forEach(sb::append);

        sb.append("Private Quota:\n");

        privateQuota.forEach(sb::append);

        sb.append("Rejected:\n");
        rejected.forEach(sb::append);
        sb.append("\n");

        return sb.toString();
    }


    public String getCode() {
        return code;
    }

    public Faculty getFaculty() {
        return faculty;
    }
}
