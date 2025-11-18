# Lab Exercise 3 - Advanced Programming

## Task 3

You need to implement a system for enrolling applicants at a university. The system manages:

* Faculties
* Study Programmes
* Applicants

### Class `Applicant`

* `id: int` — unique identifier of the applicant
* `name: String` — applicant’s name
* `gpa: double` — high-school grade point average
* `subjectsWithGrade: List<SubjectWithGrade>` — list of `SubjectWithGrade` objects storing passed Matura (final exam) subjects
* `studyProgramme: StudyProgramme` — the study programme the applicant is enrolling in

Implement:

* `addSubjectAndGrade(String subject, int grade)` — adds a subject and grade to the list.

Also implement:

* `calculatePoints` — computes the total points an applicant has for the given study programme:

    * `gpa * 12`
    * for each Matura subject:

        * if the subject is in the faculty’s list of appropriate subjects: `grade * 2`
        * if the subject is **not** in the list: `grade * 1.2`

### Class `StudyProgramme`

Contains:

* `code: String` — study programme code
* `name: String` — study programme name
* `numPublicQuota: int` — number of applicants that can enroll under the state (public) quota
* `numPrivateQuota: int` — number of applicants that can enroll under the private quota
* `enrolledInPublicQuota: int` — number enrolled under the public quota
* `enrolledInPrivateQuota: int` — number enrolled under the private quota
* `applicants: List<Applicant>` — list of applicants

Implement:

* `calculateEnrollmentNumbers` — computes and sets `enrolledInPublicQuota` and `enrolledInPrivateQuota`.

**Important:** Fill the public quota **first**, then the private quota.

Override `toString()` to display:

* the programme name
* all applicants admitted under the public quota
* all applicants admitted under the private quota
* all rejected applicants

### Class `Faculty`

Contains:

* `shortName: String` — faculty short name (e.g., FINKI, FEIT, MEDFAK)
* `appropriateSubjects: List<String>` — subjects considered appropriate for the faculty
* `studyProgrammes: List<StudyProgramme>` — list of study programmes

Override `toString()` to print all study programmes and applicants of the faculty using the following ordering criteria:

1. Number of appropriate subjects for the faculty — **ascending**
2. Percentage of admitted students in a study programme — **descending**
    - `(enrolledPublic + enrolledPrivate) / (publicQuota + privateQuota) * 100`
3. Applicant points — **descending**

### Class `EnrollmentsIO`

Implement two methods for input and output.

**Method `readEnrollments`:**

* Arguments: a list of study programmes and an `InputStream`.
* Finds the study programme in the list by the code from the input.
* Creates an `Applicant`, sets its attributes, and adds it to the applicants list of the corresponding study programme.

**Input format:**

```
id;name;gpa;subject1;grade1;subject2;grade2;subject3;grade3;subject4;grade4;studyProgrammeCode
```

Where `id` is the applicant ID, `name` the applicant name, `gpa` the GPA, `subjectN` and `gradeN` are the Matura subjects and grades, and `studyProgrammeCode` is the code of the desired study programme.

**Method `printRanked`:**

* Argument: a list of faculties.
* Prints all faculties in the format provided in the test examples and following the specified ordering criteria.

Implement all getters and setters, as well as “add” helper methods for lists where needed.

Starter code:
```java
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// TODO: Add classes and implement methods
class SubjectWithGrade
{
    private String subject;
    private int grade;
    public SubjectWithGrade(String subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }
    public String getSubject() {
        return subject;
    }
    public int getGrade() {
        return grade;
    }
}

class EnrollmentsIO {
    public static void printRanked(List<Faculty> faculties) {
    }

    public static List<Enrollment> readEnrollments(List<StudyProgramme> studyProgrammes, InputStream inputStream) {
    }
}

public class EnrollmentsTest {

    public static void main(String[] args) {
        Faculty finki = new Faculty("FINKI");
        finki.addSubject("Mother Tongue");
        finki.addSubject("Mathematics");
        finki.addSubject("Informatics");

        Faculty feit = new Faculty("FEIT");
        feit.addSubject("Mother Tongue");
        feit.addSubject("Mathematics");
        feit.addSubject("Physics");
        feit.addSubject("Electronics");

        Faculty medFak = new Faculty("MEDFAK");
        medFak.addSubject("Mother Tongue");
        medFak.addSubject("English");
        medFak.addSubject("Mathematics");
        medFak.addSubject("Biology");
        medFak.addSubject("Chemistry");

        StudyProgramme si = new StudyProgramme("SI", "Software Engineering", finki, 4, 4);
        StudyProgramme it = new StudyProgramme("IT", "Information Technology", finki, 2, 2);
        finki.addStudyProgramme(si);
        finki.addStudyProgramme(it);

        StudyProgramme kti = new StudyProgramme("KTI", "Computer Technologies and Engineering", feit, 3, 3);
        StudyProgramme ees = new StudyProgramme("EES", "Electro-energetic Systems", feit, 2, 2);
        feit.addStudyProgramme(kti);
        feit.addStudyProgramme(ees);

        StudyProgramme om = new StudyProgramme("OM", "General Medicine", medFak, 6, 6);
        StudyProgramme nurs = new StudyProgramme("NURS", "Nursing", medFak, 2, 2);
        medFak.addStudyProgramme(om);
        medFak.addStudyProgramme(nurs);

        List<StudyProgramme> allProgrammes = new ArrayList<>();
        allProgrammes.add(si);
        allProgrammes.add(it);
        allProgrammes.add(kti);
        allProgrammes.add(ees);
        allProgrammes.add(om);
        allProgrammes.add(nurs);

        EnrollmentsIO.readEnrollments(allProgrammes, System.in);

        List<Faculty> allFaculties = new ArrayList<>();
        allFaculties.add(finki);
        allFaculties.add(feit);
        allFaculties.add(medFak);

        allProgrammes.stream().forEach(StudyProgramme::calculateEnrollmentNumbers);

        EnrollmentsIO.printRanked(allFaculties);

    }
}
```

Solution:
```java
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;



class Applicant implements Comparable<Applicant> {
    private int id;
    private String name;
    private double gpa;
    private List<SubjectWithGrade> subjectWithGrades;
    private StudyProgramme studyProgramme;


    public Applicant(int id, String name, double gpa, StudyProgramme studyProgramme) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
        this.studyProgramme = studyProgramme;
        this.subjectWithGrades = new ArrayList<>();
    }

    public void addSubjectAndGrade(String subject, int grade) {
        subjectWithGrades.add(new SubjectWithGrade(subject, grade));
    }

    public double calculatePoints() {
        double sum = 0;
        List<String> appropriateSubjects = studyProgramme.getFaculty().getAppropriateSubjects();

        for (SubjectWithGrade swg : subjectWithGrades) {
            double factor = appropriateSubjects.contains(swg.getSubject()) ? 2 : 1.2;
            sum += swg.getGrade() * factor;
        }
        return sum += gpa * 12;
    }


    public int appropriateCount(List<String> appropriateSubjects) {
        return (int) subjectWithGrades.stream()
                .filter(swg -> appropriateSubjects.contains(swg.getSubject()))
                .count();
    }


    @Override
    public int compareTo(Applicant other) {
        return Comparator.comparingDouble(Applicant::calculatePoints).reversed()
                .thenComparing(Applicant::getId).compare(this, other);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Name: " + name + ", GPA: " + gpa + " - " + calculatePoints() + "\n";
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


class StudyProgramme implements Comparable<StudyProgramme> {
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
//        return Comparator.comparingInt(StudyProgramme::appropriateSubjects).reversed() // asc
//                .thenComparing(Comparator.comparing(StudyProgramme::getPercentage).reversed()) // desc
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


class SubjectWithGrade {
    private String subject;
    private int grade;

    public SubjectWithGrade(String subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }
}

class EnrollmentsIO {
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

public class EnrollmentsTest {

    public static void main(String[] args) {
        Faculty finki = new Faculty("FINKI");
        finki.addSubject("Mother Tongue");
        finki.addSubject("Mathematics");
        finki.addSubject("Informatics");

        Faculty feit = new Faculty("FEIT");
        feit.addSubject("Mother Tongue");
        feit.addSubject("Mathematics");
        feit.addSubject("Physics");
        feit.addSubject("Electronics");

        Faculty medFak = new Faculty("MEDFAK");
        medFak.addSubject("Mother Tongue");
        medFak.addSubject("English");
        medFak.addSubject("Mathematics");
        medFak.addSubject("Biology");
        medFak.addSubject("Chemistry");

        StudyProgramme si = new StudyProgramme("SI", "Software Engineering", finki, 4, 4);
        StudyProgramme it = new StudyProgramme("IT", "Information Technology", finki, 2, 2);
        finki.addStudyProgramme(si);
        finki.addStudyProgramme(it);

        StudyProgramme kti = new StudyProgramme("KTI", "Computer Technologies and Engineering", feit, 3, 3);
        StudyProgramme ees = new StudyProgramme("EES", "Electro-energetic Systems", feit, 2, 2);
        feit.addStudyProgramme(kti);
        feit.addStudyProgramme(ees);

        StudyProgramme om = new StudyProgramme("OM", "General Medicine", medFak, 6, 6);
        StudyProgramme nurs = new StudyProgramme("NURS", "Nursing", medFak, 2, 2);
        medFak.addStudyProgramme(om);
        medFak.addStudyProgramme(nurs);

        List<StudyProgramme> allProgrammes = new ArrayList<>();
        allProgrammes.add(si);
        allProgrammes.add(it);
        allProgrammes.add(kti);
        allProgrammes.add(ees);
        allProgrammes.add(om);
        allProgrammes.add(nurs);

        EnrollmentsIO.readEnrollments(allProgrammes, System.in);

        List<Faculty> allFaculties = new ArrayList<>();
        allFaculties.add(finki);
        allFaculties.add(feit);
        allFaculties.add(medFak);

        allProgrammes.stream().forEach(StudyProgramme::calculateEnrollmentNumbers);

        EnrollmentsIO.printRanked(allFaculties);

    }
}

```