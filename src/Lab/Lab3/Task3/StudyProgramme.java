package Lab.Lab3.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StudyProgramme {
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

    public String getCode() {
        return code;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public int getNumPublicQuota() {
        return numPublicQuota;
    }

    public int getNumPrivateQuota() {
        return numPrivateQuota;
    }

    public int getEnrolledInPublicQuota() {
        return enrolledInPublicQuota;
    }

    public int getEnrolledInPrivateQuota() {
        return enrolledInPrivateQuota;
    }

    public void addApplicant(Applicant applicant) {
        if (applicants.size() < numPublicQuota) {
            enrolledInPublicQuota++;
        } else if (applicants.size() < numPublicQuota + numPrivateQuota) {
            enrolledInPrivateQuota++;
        }
        applicants.add(applicant);
    }

    public void calculateEnrollmentNumbers() {
        enrolledInPublicQuota = (int) applicants.stream()
                .limit(numPublicQuota)
                .filter(Objects::nonNull)
                .count();
        if (applicants.size() >= numPublicQuota && applicants.size() < numPublicQuota + numPrivateQuota) {
            enrolledInPrivateQuota = (int) applicants.stream()
                    .skip(numPublicQuota)
                    .limit(numPrivateQuota)
                    .filter(Objects::nonNull)
                    .count();
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Public Quota:\n");
        if(!applicants.isEmpty()){
            sb.append(applicants.stream().limit(numPublicQuota).filter(Objects::nonNull).map(Applicant::toString).collect(Collectors.joining("\n"))).append("\n");
        }

        sb.append("Private Quota:\n");
        if (applicants.size() >= numPublicQuota && applicants.size() < numPublicQuota + numPrivateQuota) {
            sb.append(applicants.stream().skip(numPublicQuota).limit(numPrivateQuota).filter(Objects::nonNull).map(Applicant::toString).collect(Collectors.joining("\n"))).append("\n");
        }

        sb.append("Rejected:\n");
        if(applicants.size() >= numPublicQuota + numPrivateQuota){
            sb.append(applicants.stream().skip(numPublicQuota + numPrivateQuota).filter(Objects::nonNull).map(Applicant::toString).collect(Collectors.joining("\n"))).append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
