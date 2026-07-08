//// package Exam.SecondMidtermExam.Task50;//package mk.ukim.finki.vtor_kolokvium;
//
//import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//class OperationNotAllowedException extends Exception {
//    public OperationNotAllowedException(String message) {
//        super(message);
//    }
//}
//
//class Semester {
//    private int term;
//    private String studentId;
//    private Map<String, Integer> courses;
//
//    public Semester(int term, String studentId) {
//        this.term = term;
//        this.studentId = studentId;
//        this.courses = new TreeMap<>();
//    }
//
//    public void addGrade(String courseName, int grade, Map<String, Integer> allCourses) throws OperationNotAllowedException {
//        checkCondition(courseName);
//
//        courses.put(courseName, grade);
//        allCourses.put(courseName, grade);
//    }
//
//    private void checkCondition(String courseName) throws OperationNotAllowedException {
////        if (!courses.containsKey(courseName) && courses.size() == 3) {
////            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d", studentId, term));
////        }
//        if (courses.size() == 3) {
//            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d", studentId, term));
//        }
//    }
//
//    public double avgGrade() {
//        return courses.values().stream().mapToInt(c -> c).average().orElse(5.0);
//    }
//
//    public static String print(int term, int numCourses, double avgGrade) {
//        return String.format("Term %d\nCourses: %d\nAverage grade for term: %.2f", term, numCourses, avgGrade);
//    }
//
//    @Override
//    public String toString() {
//        return print(term, courses.size(), avgGrade());
//    }
//
//    public static String getDefaultPrinting(int term) {
//        return print(term, 0, 5.0);
//    }
//
//}
//
//class Course implements Comparable<Course> {
//    private String courseName;
//    private List<Integer> grades;
//    private int countOfStudents;
//
//    public Course(String courseName) {
//        this.courseName = courseName;
//        this.countOfStudents = 0;
//        this.grades = new ArrayList<>();
//    }
//
//    public void addGrade(int grade) {
//        grades.add(grade);
//        countOfStudents++;
//    }
//
//    public double avgGrade() {
//        return grades.stream().mapToInt(i -> i).average().orElse(5.0);
//    }
//
//    public int getCountOfStudents() {
//        return countOfStudents;
//    }
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    @Override
//    public int compareTo(Course o) {
//        return Comparator.comparing(Course::getCountOfStudents)
//                .thenComparing(Course::avgGrade)
//                .thenComparing(Course::getCourseName)
//                .compare(this,o);
//    }
//
//    @Override
//    public String toString() {
//        //course18 5 6.00↩
//        return String.format("%s %d %.2f", courseName, countOfStudents, avgGrade(), grades);
//    }
//}
//
//class Student implements Comparable<Student> {
//    private String id;
//    private int yearsOfStudies;
//    private Map<Integer, Semester> semesters;
//    private Map<String, Integer> courses;
//
//    public Student(String id, int yearsOfStudies) {
//        this.id = id;
//        this.yearsOfStudies = yearsOfStudies;
//        this.semesters = new TreeMap<>();
//        this.courses = new TreeMap<>();
//    }
//
//    public int getGradeForCourse(String courseName) {
//        return courses.get(courseName);
//    }
//
//    public void addGradeToSemester(int term, String courseName, int grade) throws OperationNotAllowedException {
//        checkTermCondition(term);
//        semesters.computeIfAbsent(term, k -> new Semester(term, id)).addGrade(courseName, grade, courses);
//    }
//
//    private void checkTermCondition(int term) throws OperationNotAllowedException {
//        boolean threeYearCondition = (yearsOfStudies == 3 && term > 6);
//        boolean fourYearCondition = (yearsOfStudies == 4 && term > 8);
////        if (!semesters.containsKey(term) && (threeYearCondition || fourYearCondition)) {
////            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s", term, id));
////        }
//        if ((threeYearCondition || fourYearCondition)) {
//            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s", term, id));
//        }
//    }
//
//    public int passedCourses() {
//        return courses.size();
//    }
//
//    public boolean hasGraduated() {
//        int numOfPassedCourses = yearsOfStudies == 3 ? 18 : 24;
////        return courses.size() == numOfPassedCourses && !courses.containsValue(5);
//        return courses.size() == numOfPassedCourses;
//    }
//
//    public double avgGrade() {
//        return courses.values().stream().mapToInt(c -> c).average().orElse(5.0);
//    }
//
//    public String getReport() {
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("Student: ").append(id).append("\n");
//        int numSemesters = yearsOfStudies == 3 ? 6 : 8;
//        for (int i = 1; i < numSemesters + 1; i++) {
//            Semester semester = semesters.get(i);
//            if (semester == null) {
//                sb.append(Semester.getDefaultPrinting(i)).append("\n");
//                continue;
//            }
//            sb.append(semester).append("\n");
//        }
//
//        sb.append("Average grade: ").append(String.format("%.2f", avgGrade())).append("\n");
//        sb.append("Courses attended: ").append(String.join(",", courses.keySet()));
//        return sb.toString();
//    }
//
//    public String getGraduationMessage() {
//        //Student with ID [studentID] graduated with average grade [averageGrade] in [yearsOfStudies] years
//        return String.format("Student with ID %s graduated with average grade %.2f in %d years.", id, avgGrade(), yearsOfStudies);
//    }
//
//    @Override
//    public String toString() {
//        //Student: [id] Courses passed: [coursesPassed] Average grade: [averageGrade]
//        return String.format("Student: %s Courses passed: %d Average grade: %.2f", id, passedCourses(), avgGrade());
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    @Override
//    public int compareTo(Student o) {
//        return Comparator.comparing(Student::passedCourses)
//                .thenComparing(Student::avgGrade)
//                .thenComparing(Student::getId)
//                .reversed()
//                .compare(this, o);
//    }
//}
//
//
//class Faculty {
//    private Map<String, Student> students;
//    private Map<String, Course> courses;
//    private List<String> logs;
//
//    public Faculty() {
//        this.students = new HashMap<>();
//        this.courses = new HashMap<>();
//        this.logs = new ArrayList<>();
//    }
//
//    void addStudent(String id, int yearsOfStudies) {
//        students.putIfAbsent(id, new Student(id, yearsOfStudies));
//    }
//
//    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
//        Student student = students.get(studentId);
//        student.addGradeToSemester(term, courseName, grade);
//        courses.computeIfAbsent(courseName, k -> new Course(courseName)).addGrade(grade);
//        if (student.hasGraduated()) {
//            students.remove(studentId);
//            logs.add(student.getGraduationMessage());
//        }
//    }
//
//    public String getFacultyLogs() {
//        return String.join("\n", logs);
//    }
//
//    String getDetailedReportForStudent(String id) {
//        return students.get(id).getReport();
//    }
//
//    void printFirstNStudents(int n) {
//        new TreeSet<>(students.values())
//                .stream()
//                .limit(n)
//                .forEach(System.out::println);
//    }
//
//    void printCourses() {
//        new TreeSet<>(courses.values())
//                .forEach(System.out::println);
//    }
//}
//
//public class FacultyTest {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int testCase = sc.nextInt();
//
//        if (testCase == 1) {
//            System.out.println("TESTING addStudent AND printFirstNStudents");
//            Faculty faculty = new Faculty();
//            for (int i = 0; i < 10; i++) {
//                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
//            }
//            faculty.printFirstNStudents(10);
//
//        } else if (testCase == 2) {
//            System.out.println("TESTING addGrade and exception");
//            Faculty faculty = new Faculty();
//            faculty.addStudent("123", 3);
//            faculty.addStudent("1234", 4);
//            try {
//                faculty.addGradeToStudent("123", 7, "NP", 10);
//            } catch (OperationNotAllowedException e) {
//                System.out.println(e.getMessage());
//            }
//            try {
//                faculty.addGradeToStudent("1234", 9, "NP", 8);
//            } catch (OperationNotAllowedException e) {
//                System.out.println(e.getMessage());
//            }
//        } else if (testCase == 3) {
//            System.out.println("TESTING addGrade and exception");
//            Faculty faculty = new Faculty();
//            faculty.addStudent("123", 3);
//            faculty.addStudent("1234", 4);
//            for (int i = 0; i < 4; i++) {
//                try {
//                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
//                } catch (OperationNotAllowedException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//            for (int i = 0; i < 4; i++) {
//                try {
//                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
//                } catch (OperationNotAllowedException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        } else if (testCase == 4) {
//            System.out.println("Testing addGrade for graduation");
//            Faculty faculty = new Faculty();
//            faculty.addStudent("123", 3);
//            faculty.addStudent("1234", 4);
//            int counter = 1;
//            for (int i = 1; i <= 6; i++) {
//                for (int j = 1; j <= 3; j++) {
//                    try {
//                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
//                    } catch (OperationNotAllowedException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    ++counter;
//                }
//            }
//            counter = 1;
//            for (int i = 1; i <= 8; i++) {
//                for (int j = 1; j <= 3; j++) {
//                    try {
//                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
//                    } catch (OperationNotAllowedException e) {
//                        System.out.println(e.getMessage());
//                    }
//                    ++counter;
//                }
//            }
//            System.out.println("LOGS");
//            System.out.println(faculty.getFacultyLogs());
//            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
//            faculty.printFirstNStudents(2);
//        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
//            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
//            Faculty faculty = new Faculty();
//            for (int i = 1; i <= 10; i++) {
//                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
//                int courseCounter = 1;
//                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
//                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
//                        try {
//                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
//                        } catch (OperationNotAllowedException e) {
//                            System.out.println(e.getMessage());
//                        }
//                        ++courseCounter;
//                    }
//                }
//            }
//            if (testCase == 5)
//                faculty.printFirstNStudents(10);
//            else if (testCase == 6)
//                faculty.printFirstNStudents(3);
//            else
//                faculty.printFirstNStudents(20);
//        } else if (testCase == 8 || testCase == 9) {
//            System.out.println("TESTING DETAILED REPORT");
//            Faculty faculty = new Faculty();
//            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
//            int grade = 6;
//            int counterCounter = 1;
//            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
//                for (int j = 1; j < 3; j++) {
//                    try {
//                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
//                    } catch (OperationNotAllowedException e) {
//                        e.printStackTrace();
//                    }
//                    grade++;
//                    if (grade == 10)
//                        grade = 5;
//                    ++counterCounter;
//                }
//            }
//            System.out.println(faculty.getDetailedReportForStudent("student1"));
//        } else if (testCase == 10) {
//            System.out.println("TESTING PRINT COURSES");
//            Faculty faculty = new Faculty();
//            for (int i = 1; i <= 10; i++) {
//                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
//                int courseCounter = 1;
//                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
//                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
//                        int grade = sc.nextInt();
//                        try {
//                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
//                        } catch (OperationNotAllowedException e) {
//                            System.out.println(e.getMessage());
//                        }
//                        ++courseCounter;
//                    }
//                }
//            }
//            faculty.printCourses();
//        } else if (testCase == 11) {
//            System.out.println("INTEGRATION TEST");
//            Faculty faculty = new Faculty();
//            for (int i = 1; i <= 10; i++) {
//                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
//                int courseCounter = 1;
//                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
//                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
//                        int grade = sc.nextInt();
//                        try {
//                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
//                        } catch (OperationNotAllowedException e) {
//                            System.out.println(e.getMessage());
//                        }
//                        ++courseCounter;
//                    }
//                }
//
//            }
//
//            for (int i = 11; i < 15; i++) {
//                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
//                int courseCounter = 1;
//                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
//                    for (int k = 1; k <= 3; k++) {
//                        int grade = sc.nextInt();
//                        try {
//                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
//                        } catch (OperationNotAllowedException e) {
//                            System.out.println(e.getMessage());
//                        }
//                        ++courseCounter;
//                    }
//                }
//            }
//            System.out.println("LOGS");
//            System.out.println(faculty.getFacultyLogs());
//            System.out.println("DETAILED REPORT FOR STUDENT");
//            System.out.println(faculty.getDetailedReportForStudent("student2"));
//            try {
//                System.out.println(faculty.getDetailedReportForStudent("student11"));
//                System.out.println("The graduated students should be deleted!!!");
//            } catch (NullPointerException e) {
//                System.out.println("The graduated students are really deleted");
//            }
//            System.out.println("FIRST N STUDENTS");
//            faculty.printFirstNStudents(10);
//            System.out.println("COURSES");
//            faculty.printCourses();
//        }
//    }
//}
