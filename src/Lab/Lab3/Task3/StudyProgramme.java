package Lab.Lab3.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class StudyProgramme implements Comparable<StudyProgramme> {
    private String code;
    private String name;
    private int numPublicQuota;
    private int numPrivateQuota;
    private int enrolledPublic;
    private int enrolledPrivate;
    private List<Applicant> applicants;
    private Faculty faculty;

    public StudyProgramme(String code, String name, Faculty faculty, int numPublicQuota, int numPrivateQuota) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.numPublicQuota = numPublicQuota;
        this.numPrivateQuota = numPrivateQuota;
        this.enrolledPublic = 0;
        this.enrolledPrivate = 0;
        this.applicants = new ArrayList<>();
    }

    public void calculateEnrollmentNumbers() {
        enrolledPublic = Math.min(numPublicQuota, applicants.size());
        enrolledPrivate = Math.min(applicants.size() - enrolledPublic, numPrivateQuota);
    }

    public void addApplicant(Applicant applicant) {
        applicants.add(applicant);
    }

    public String getCode() {
        return code;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public double percentage() {
        //(enrolledPublic + enrolledPrivate) / (publicQuota + privateQuota) * 100
        return (enrolledPublic + enrolledPrivate) * 1.0 / (numPublicQuota + numPrivateQuota) * 100;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        applicants = applicants.stream().sorted().collect(Collectors.toList());

        sb.append("Name: ").append(name).append("\n");
        sb.append("Public Quota:\n");
        int index = 0;
        while (index < enrolledPublic && index < applicants.size()) {
            sb.append(applicants.get(index)).append("\n");
            index++;
        }

        sb.append("Private Quota:\n");
        int criteria1 = enrolledPublic + enrolledPrivate;
        while (index >= enrolledPublic && index < enrolledPublic + enrolledPrivate && index < applicants.size()) {
            sb.append(applicants.get(index)).append("\n");
            index++;
        }
        sb.append("Rejected:\n");
        while (index < applicants.size()) {
            sb.append(applicants.get(index)).append("\n");
            index++;
        }
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public int compareTo(StudyProgramme o) {
        return Double.compare(o.percentage(), percentage());
    }
}
