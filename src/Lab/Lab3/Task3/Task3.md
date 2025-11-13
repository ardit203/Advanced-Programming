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
id;name;gpa;subject1:grade1;subject2:grade2;subject3:grade3;subject4:grade4;studyProgrammeCode
```

Where `id` is the applicant ID, `name` the applicant name, `gpa` the GPA, `subjectN` and `gradeN` are the Matura subjects and grades, and `studyProgrammeCode` is the code of the desired study programme.

**Method `printRanked`:**

* Argument: a list of faculties.
* Prints all faculties in the format provided in the test examples and following the specified ordering criteria.

Implement all getters and setters, as well as “add” helper methods for lists where needed.
