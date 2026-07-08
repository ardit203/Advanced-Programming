Implement an application for recording student grades at a faculty. The students at the faculty can be enrolled in **three-year** or **four-year** studies. During their studies, students have **two** semesters in each year and in each semester they can have at most **3 courses**. For this purpose, define a class `Faculty` in which you will store information about the students and their grades in all semesters. For the class, implement:

* Default constructor `Faculty()`

* Method `void addStudent(String id, int yearsOfStudies)` – for adding a student to the faculty with index `id` and years of studies `yearsOfStudies`.

* Method `void addGradeToStudent(String studentId, int term, String courseName, int grade)` – for adding a grade `grade` in the course `courseName` for the student with index `studentId` in semester `term`.

    * Using an exception of type `OperationNotAllowedException`, prevent adding more than 3 grades per semester. In such a case, print a message in the format `Student [studentID] already has 3 grades in term [term]`. Using the same type of exception, prevent adding a grade in a semester greater than 6 for three-year studies, or in a semester greater than 8 for four-year studies. In this case, print the message `Term [term] is not possible for student with ID [studentId]`.
    * Graduation of the student should be detected. The student graduates when they pass 18 or 24 courses depending on how many years they study. At the moment of the student’s graduation, they should be removed from the records and a log for them should be saved in the format `Student with ID [studentID] graduated with average grade [averageGrade] in [yearsOfStudies] years`.

* Method `String getFacultyLogs()` – which returns the logs for the graduated students.

* Method `String getDetailedReportForStudent(String id)` – method that returns a detailed report for the student with index `id`. Access to the student with index `id` must have complexity `O(1)`! The detailed report is in the format:

  > Student: [id]
  > 
  > Term 1:
  > 
  > Courses for term: [count]
  > 
  > Average grade for term: [average]
  > 
  > …
  > 
  > …..
  > 
  > Term n:
  > 
  > Courses: [count]
  > 
  > Average grade for term: [average]
  > 
  > Average grade: [average grade for student]
  > 
  > Courses attended: [all_attended_courses, comma-separated, sorted lexicographically]

* Method `void printFirstNStudents(int n)` – method that prints a short report for the best `n` students (according to the number of passed courses, and if the number of passed courses is the same, according to the average grade), sorted in descending order. The short report is in the format `Student: [id] Courses passed: [coursesPassed] Average grade: [averageGrade]`.

* Method `void printCourses()` – method that prints all courses in the format `[course_name] [count_of_students] [average_grade]` for the faculty, sorted by the number of students attending the course, and if that is the same, by the average grade.

It is forbidden to use sorting functions; the sorting must be implemented using appropriate collections!


### Starter code
```java
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Faculty {
  

    public Faculty() {
        
    }

    void addStudent(String id, int yearsOfStudies) {
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        
    }

    String getFacultyLogs() {
        return "";
    }

    String getDetailedReportForStudent(String id) {
        return "";
    }

    void printFirstNStudents(int n) {
        
    }

    void printCourses() {
        
    }
}

public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase==10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase==11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i=11;i<15;i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}
```

### Solution
```java
import java.util.*;



class OperationNotAllowedException extends Exception {
    public OperationNotAllowedException(String message) {
        super(message);
    }
}

class Semester {
    private int term;
    private String studentId;
    private Map<String, Integer> courses;//holds the course and the grade in that course in the term

    public Semester(int term, String studentId) {
        this.term = term;
        this.studentId = studentId;
        this.courses = new TreeMap<>();
    }

    public void addGrade(String courseName, int grade, Map<String, Integer> allCourses) throws OperationNotAllowedException {
        if (courses.size() == 3) {
            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d", studentId, term));
        }
        courses.put(courseName, grade);//student can pass a course with grade 5
        allCourses.put(courseName, grade);//this keeps track of all the courses and grades of a student
    }

    public double avgGrade() {//calculating avgGrade for term
        return courses.values().stream().mapToInt(c -> c).average().orElse(5.0);
    }

    public static String print(int term, int numCourses, double avgGrade) {
        return String.format("Term %d\nCourses: %d\nAverage grade for term: %.2f", term, numCourses, avgGrade);
    }

    @Override
    public String toString() {
        return print(term, courses.size(), avgGrade());
    }

    public static String getDefaultPrinting(int term) {//This is used if a student is in term 5 for example,
        // and he hasn't enrolled in term 6 but the testCases require u to print all the terms even if no enrollment happened
        return print(term, 0, 5.0);
    }

}

class Student implements Comparable<Student> {
    private String id;
    private int yearsOfStudies;
    private Map<Integer, Semester> semesters;//keeps the term as the key and the term(Semester) as value
    private Map<String, Integer> courses;//keeps track of all the courses and grades of a student

    public Student(String id, int yearsOfStudies) {
        this.id = id;
        this.yearsOfStudies = yearsOfStudies;
        this.semesters = new TreeMap<>();
        this.courses = new TreeMap<>();
    }

    public void addGradeToSemester(int term, String courseName, int grade) throws OperationNotAllowedException {
        checkTermCondition(term);
        semesters.computeIfAbsent(term, k -> new Semester(term, id)).addGrade(courseName, grade, courses);
    }

    private void checkTermCondition(int term) throws OperationNotAllowedException {
        boolean threeYearCondition = (yearsOfStudies == 3 && term > 6);
        boolean fourYearCondition = (yearsOfStudies == 4 && term > 8);

        if (threeYearCondition || fourYearCondition) {
            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s", term, id));
        }
    }

    public int passedCourses() {//returns the number of passed courses, a course is passed even with grade 5
        return courses.size();
    }

    public boolean hasGraduated() {//checks if a student has graduated, the student can have 5's yet still graduate
        int numOfPassedCourses = yearsOfStudies == 3 ? 18 : 24;
        return courses.size() == numOfPassedCourses;
    }

    public double avgGrade() {//avgGrad of a student
        return courses.values().stream().mapToInt(c -> c).average().orElse(5.0);
    }

    public String getReport() {//prints the report for a student
        StringBuilder sb = new StringBuilder();

        sb.append("Student: ").append(id).append("\n");
        int numSemesters = yearsOfStudies == 3 ? 6 : 8;
        for (int i = 1; i < numSemesters + 1; i++) {
            Semester semester = semesters.get(i);
            if (semester == null) {
                sb.append(Semester.getDefaultPrinting(i)).append("\n");
                continue;
            }
            sb.append(semester).append("\n");
        }

        sb.append("Average grade: ").append(String.format("%.2f", avgGrade())).append("\n");
        sb.append("Courses attended: ").append(String.join(",", courses.keySet()));
        return sb.toString();
    }

    public String getGraduationMessage() {
        //Student with ID [studentID] graduated with average grade [averageGrade] in [yearsOfStudies] years
        return String.format("Student with ID %s graduated with average grade %.2f in %d years.", id, avgGrade(), yearsOfStudies);
    }

    @Override
    public String toString() {
        //Student: [id] Courses passed: [coursesPassed] Average grade: [averageGrade]
        return String.format("Student: %s Courses passed: %d Average grade: %.2f", id, passedCourses(), avgGrade());
    }

    public String getId() {
        return id;
    }

    @Override
    public int compareTo(Student o) {
        return Comparator.comparing(Student::passedCourses)
                .thenComparing(Student::avgGrade)
                .thenComparing(Student::getId)
                .reversed()
                .compare(this, o);
    }
}

class Course implements Comparable<Course> {
    private String courseName;
    private List<Integer> grades;

    public Course(String courseName) {
        this.courseName = courseName;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) {
        grades.add(grade);
    }

    public double avgGrade() {
        return grades.stream().mapToInt(i -> i).average().orElse(5.0);
    }

    public int getCountOfStudents() {
        return grades.size();
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public int compareTo(Course o) {
        return Comparator.comparing(Course::getCountOfStudents)
                .thenComparing(Course::avgGrade)
                .thenComparing(Course::getCourseName)
                .compare(this, o);
    }

    @Override
    public String toString() {
        //course18 5 6.00↩
        return String.format("%s %d %.2f", courseName, getCountOfStudents(), avgGrade());
    }
}

class Faculty {
    private Map<String, Student> students;//keeps track of all students by id
    private Map<String, Course> courses;//all the courses that students enrolled with id courseName,
    // and inside keeps the courseName, and all the grades the students got in the specific course
    private List<String> logs; //if a student graduates we add the graduation message to this list

    public Faculty() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        this.logs = new ArrayList<>();
    }

    void addStudent(String id, int yearsOfStudies) {
        students.putIfAbsent(id, new Student(id, yearsOfStudies));
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        Student student = students.get(studentId);
        student.addGradeToSemester(term, courseName, grade);
        courses.computeIfAbsent(courseName, k -> new Course(courseName)).addGrade(grade);
        if (student.hasGraduated()) {
            students.remove(studentId);
            logs.add(student.getGraduationMessage());
        }
    }

    public String getFacultyLogs() {
        return String.join("\n", logs);
    }

    String getDetailedReportForStudent(String id) {
        return students.get(id).getReport();
    }

    void printFirstNStudents(int n) {//prints the top students, because we are not allowed to use sort methods,
        // we add all students to a TreeSet which keeps the students sorted using the compareTo method inside Student
        new TreeSet<>(students.values())
                .stream()
                .limit(n)
                .forEach(System.out::println);
    }

    void printCourses() {//same ide as printFirstNStudents
        new TreeSet<>(courses.values())
                .forEach(System.out::println);
    }
}

public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase == 10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase == 11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i = 11; i < 15; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}
```