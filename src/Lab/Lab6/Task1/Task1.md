# Lab Exercise 6 - Advanced Programming

<div class="clearfix" id="yui_3_18_1_1_1765235714282_85"><p data-start="262" data-end="499" id="yui_3_18_1_1_1765235714282_84">You are given three classes: <code data-start="291" data-end="299">Course</code>, <code data-start="301" data-end="313">Department</code>, and <code data-start="319" data-end="331">University</code>.<br data-start="332" data-end="335">The classes <code data-start="347" data-end="355">Course</code> and <code data-start="360" data-end="372">Department</code> are fully implemented.<br data-start="395" data-end="398">Your task is to implement all methods in <code data-start="439" data-end="451" id="yui_3_18_1_1_1765235714282_83">University</code> <strong data-start="452" data-end="479">using Java Streams only</strong> (no loops allowed).</p>
<ul data-start="543" data-end="1333">
<li data-start="543" data-end="901">
<p data-start="545" data-end="555"><strong data-start="545" data-end="555">Course</strong></p>
<ul data-start="558" data-end="901">
<li data-start="558" data-end="591">
<p data-start="560" data-end="591">Represents a university course.</p>
</li>
<li data-start="594" data-end="843">
<p data-start="596" data-end="603">Fields:</p>
<ul data-start="608" data-end="843">
<li data-start="608" data-end="651">
<p data-start="610" data-end="651"><code data-start="610" data-end="623">String code</code> – course code (e.g., "OOP")</p>
</li>
<li data-start="656" data-end="690">
<p data-start="658" data-end="690"><code data-start="658" data-end="671">String name</code> – full course name</p>
</li>
<li data-start="695" data-end="735">
<p data-start="697" data-end="735"><code data-start="697" data-end="710">int credits</code> – number of ECTS credits</p>
</li>
<li data-start="740" data-end="784">
<p data-start="742" data-end="784"><code data-start="742" data-end="758">int difficulty</code> – difficulty level (1–10)</p>
</li>
<li data-start="789" data-end="843">
<p data-start="791" data-end="843"><code data-start="791" data-end="813">int enrolledStudents</code> – number of enrolled students</p>
</li>
</ul>
</li>
<li data-start="846" data-end="901">
<p data-start="848" data-end="901">Contains only getters. No implementation is required.</p>
</li>
</ul>
</li>
<li data-start="903" data-end="1133">
<p data-start="905" data-end="919"><strong data-start="905" data-end="919">Department</strong></p>
<ul data-start="922" data-end="1133">
<li data-start="922" data-end="959">
<p data-start="924" data-end="959">Represents a university department.</p>
</li>
<li data-start="962" data-end="1075">
<p data-start="964" data-end="971">Fields:</p>
<ul data-start="976" data-end="1075">
<li data-start="976" data-end="1009">
<p data-start="978" data-end="1009"><code data-start="978" data-end="991">String name</code> – department name</p>
</li>
<li data-start="1014" data-end="1075">
<p data-start="1016" data-end="1075"><code data-start="1016" data-end="1038">List&lt;Course&gt; courses</code> – courses offered by this department</p>
</li>
</ul>
</li>
<li data-start="1078" data-end="1133">
<p data-start="1080" data-end="1133">Contains only getters. No implementation is required.</p>
</li>
</ul>
</li>
<li data-start="1135" data-end="1333">
<p data-start="1137" data-end="1151"><strong data-start="1137" data-end="1151">University</strong></p>
<ul data-start="1154" data-end="1333">
<li data-start="1154" data-end="1212">
<p data-start="1156" data-end="1212">Represents a university containing multiple departments.</p>
</li>
<li data-start="1215" data-end="1260">
<p data-start="1217" data-end="1223">Field:</p>
<ul data-start="1228" data-end="1260">
<li data-start="1228" data-end="1260">
<p data-start="1230" data-end="1260"><code data-start="1230" data-end="1260">List&lt;Department&gt; departments</code></p>
</li>
</ul>
</li>
<li data-start="1263" data-end="1333">
<p data-start="1265" data-end="1333">Students must implement all provided methods using <strong data-start="1316" data-end="1332">Streams only</strong>.</p>
</li>
</ul>
</li>
</ul>
<hr data-start="1335" data-end="1338">
<h5 data-start="1340" data-end="1387"><strong data-start="1343" data-end="1387">Methods to implement (with descriptions)</strong></h5>
<ol data-start="1389" data-end="3792">
<li data-start="1389" data-end="1482">
<p data-start="1392" data-end="1415"><code data-start="1392" data-end="1413">getAllCourseNames()</code></p>
<ul data-start="1419" data-end="1482">
<li data-start="1419" data-end="1482">
<p data-start="1421" data-end="1482">Return a list of the names of all courses in all departments.</p>
</li>
</ul>
</li>
<li data-start="1484" data-end="1585">
<p data-start="1487" data-end="1531"><code data-start="1487" data-end="1529">getCoursesWithMinCredits(int minCredits)</code></p>
<ul data-start="1535" data-end="1585">
<li data-start="1535" data-end="1585">
<p data-start="1537" data-end="1585">Return all courses where <code data-start="1562" data-end="1584">credits ≥ minCredits</code>.</p>
</li>
</ul>
</li>
<li data-start="1587" data-end="1686">
<p data-start="1590" data-end="1616"><code data-start="1590" data-end="1614">getTotalStudentCount()</code></p>
<ul data-start="1620" data-end="1686">
<li data-start="1620" data-end="1686">
<p data-start="1622" data-end="1686">Return the total number of enrolled students across all courses.</p>
</li>
</ul>
</li>
<li data-start="1688" data-end="1772">
<p data-start="1691" data-end="1713"><code data-start="1691" data-end="1711">getHardestCourse()</code></p>
<ul data-start="1717" data-end="1772">
<li data-start="1717" data-end="1772">
<p data-start="1719" data-end="1772">Return the course with the highest difficulty rating.</p>
</li>
</ul>
</li>
<li data-start="1774" data-end="1891">
<p data-start="1777" data-end="1800"><code data-start="1777" data-end="1798">groupByDifficulty()</code></p>
<ul data-start="1804" data-end="1891">
<li data-start="1804" data-end="1891">
<p data-start="1806" data-end="1891">Group all courses by their difficulty.<br data-start="1844" data-end="1847">Result: <code data-start="1860" data-end="1890">difficulty → list of courses</code>.</p>
</li>
</ul>
</li>
<li data-start="1893" data-end="1976">
<p data-start="1896" data-end="1924"><code data-start="1896" data-end="1922">getCourseEnrollmentMap()</code></p>
<ul data-start="1928" data-end="1976">
<li data-start="1928" data-end="1976">
<p data-start="1930" data-end="1976">Return a map: <code data-start="1944" data-end="1975">courseCode → enrolledStudents</code>.</p>
</li>
</ul>
</li>
<li data-start="1978" data-end="2071">
<p data-start="1981" data-end="2016"><code data-start="1981" data-end="2014">getAverageEnrollmentPerCourse()</code></p>
<ul data-start="2020" data-end="2071">
<li data-start="2020" data-end="2071">
<p data-start="2022" data-end="2071">Return the average number of students per course.</p>
</li>
</ul>
</li>
<li data-start="2073" data-end="2154">
<p data-start="2076" data-end="2102"><code data-start="2076" data-end="2100">getSortedCourseCodes()</code></p>
<ul data-start="2106" data-end="2154">
<li data-start="2106" data-end="2154">
<p data-start="2108" data-end="2154">Return all course codes sorted alphabetically.</p>
</li>
</ul>
</li>
<li data-start="2156" data-end="2251">
<p data-start="2159" data-end="2191"><code data-start="2159" data-end="2189">getDepartmentToCourseNames()</code></p>
<ul data-start="2195" data-end="2251">
<li data-start="2195" data-end="2251">
<p data-start="2197" data-end="2251">Return a map: <code data-start="2211" data-end="2250">departmentName → list of course names</code>.</p>
</li>
</ul>
</li>
<li data-start="2253" data-end="2350">
<p data-start="2257" data-end="2280"><code data-start="2257" data-end="2278">getAllCourses()</code></p>
<ul data-start="2285" data-end="2350">
<li data-start="2285" data-end="2350">
<p data-start="2287" data-end="2350">Return a flat list containing all courses from all departments.</p>
</li>
</ul>
</li>
<li data-start="2352" data-end="2466">
<p data-start="2356" data-end="2386"><code data-start="2356" data-end="2384">getMostPopularDepartment()</code></p>
<ul data-start="2391" data-end="2466">
<li data-start="2391" data-end="2466">
<p data-start="2393" data-end="2466">Return the department with the highest total number of enrolled students.</p>
</li>
</ul>
</li>
<li data-start="2468" data-end="2611">
<p data-start="2472" data-end="2501"><code data-start="2472" data-end="2499">getStudentsByDifficulty()</code></p>
<ul data-start="2506" data-end="2611">
<li data-start="2506" data-end="2611">
<p data-start="2508" data-end="2611">Return a map:<br data-start="2521" data-end="2524"><code data-start="2530" data-end="2569">difficulty → total number of students</code> enrolled in courses with that difficulty.</p>
</li>
</ul>
</li>
<li data-start="2613" data-end="2744">
<p data-start="2617" data-end="2666"><code data-start="2617" data-end="2664">getCoursesByDifficultyRange(int min, int max)</code></p>
<ul data-start="2671" data-end="2744">
<li data-start="2671" data-end="2744">
<p data-start="2673" data-end="2744">Return all courses with difficulty between <code data-start="2716" data-end="2721">min</code> and <code data-start="2726" data-end="2731">max</code> (inclusive).</p>
</li>
</ul>
</li>
<li data-start="2746" data-end="2866">
<p data-start="2750" data-end="2792"><code data-start="2750" data-end="2790">getPopularCourseCodes(int minStudents)</code></p>
<ul data-start="2797" data-end="2866">
<li data-start="2797" data-end="2866">
<p data-start="2799" data-end="2866">Return the course codes of all courses with at least <code data-start="2852" data-end="2865">minStudents</code>.</p>
</li>
</ul>
</li>
<li data-start="2868" data-end="2982">
<p data-start="2872" data-end="2906"><code data-start="2872" data-end="2904">getTotalCreditsPerDepartment()</code></p>
<ul data-start="2911" data-end="2982">
<li data-start="2911" data-end="2982">
<p data-start="2913" data-end="2982">Return a map: <code data-start="2927" data-end="2981">departmentName → total sum of credits of its courses</code>.</p>
</li>
</ul>
</li>
<li data-start="2984" data-end="3075">
<p data-start="2988" data-end="3015"><code data-start="2988" data-end="3013">getTop3HardestCourses()</code></p>
<ul data-start="3020" data-end="3075">
<li data-start="3020" data-end="3075">
<p data-start="3022" data-end="3075">Return the three courses with the highest difficulty.</p>
</li>
</ul>
</li>
<li data-start="3077" data-end="3179">
<p data-start="3081" data-end="3120"><code data-start="3081" data-end="3118">getAverageDifficultyPerDepartment()</code></p>
<ul data-start="3125" data-end="3179">
<li data-start="3125" data-end="3179">
<p data-start="3127" data-end="3179">Return a map: <code data-start="3141" data-end="3178">departmentName → average difficulty</code>.</p>
</li>
</ul>
</li>
<li data-start="3293" data-end="3447">
<p data-start="3297" data-end="3326"><code data-start="3297" data-end="3324">getEnrollmentStatistics()</code></p>
<ul data-start="3331" data-end="3447">
<li data-start="3331" data-end="3447">
<p data-start="3333" data-end="3447">Return an <code data-start="3343" data-end="3365">IntSummaryStatistics</code> object over enrolled students, containing:<br data-start="3408" data-end="3411">count, sum, min, max, average.</p>
</li>
</ul>
</li>
<li data-start="3449" data-end="3792">
<p data-start="3453" data-end="3487"><code data-start="3453" data-end="3485">mergeFourSmallestDepartments()</code></p>
<ul data-start="3492" data-end="3792">
<li data-start="3492" data-end="3565">
<p data-start="3494" data-end="3565">Find the four departments with the smallest total number of students.</p>
</li>
<li data-start="3570" data-end="3600">
<p data-start="3572" data-end="3600">Merge them using <code data-start="3589" data-end="3597">reduce</code>.</p>
</li>
<li data-start="3605" data-end="3679">
<p data-start="3607" data-end="3679">The merged department name must be: <code data-start="3643" data-end="3676">"DeptA &amp; DeptB &amp; DeptC &amp; DeptD"</code>.</p>
</li>
<li data-start="3684" data-end="3721">
<p data-start="3686" data-end="3721">All courses must be concatenated.</p>
</li>
<li data-start="3726" data-end="3792">
<p data-start="3728" data-end="3792">Return a new University object with the updated department list.</p>
</li>
</ul>
</li>
</ol>
</div>

### Starter code:
```java
package Lab.Lab6.Task1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

class Course {
    private String code;
    private String name;
    private int credits;
    private int difficulty;
    private int enrolledStudents;

    public Course(String code, String name, int credits, int difficulty, int enrolledStudents) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.difficulty = difficulty;
        this.enrolledStudents = enrolledStudents;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, name);
    }
}


class Department {
    private String name;
    private List<Course> courses;

    public Department(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return name;
    }
}


class University {

    private List<Department> departments;

    public University(List<Department> departments) {
        this.departments = departments;
    }


    public List<String> getAllCourseNames() {
        //TODO
    }

    public List<Course> getCoursesWithMinCredits(int minCredits) {
        //TODO
    }

    public int getTotalStudentCount() {
        //TODO
    }

    public Optional<Course> getHardestCourse() {
        //TODO
    }

    public Map<Integer, List<Course>> groupByDifficulty() {
        //TODO
    }

    public Map<String, Integer> getCourseEnrollmentMap() {
        //TODO
    }

    public double getAverageEnrollmentPerCourse() {
        //TODO
    }

    public List<String> getSortedCourseCodes() {
        //TODO
    }

    public Map<String, List<String>> getDepartmentToCourseNames() {
        //TODO
    }

    public List<Course> getAllCourses() {
        //TODO
    }

    public Optional<Department> getMostPopularDepartment() {
        //TODO
    }

    public Map<Integer, Integer> getStudentsByDifficulty() {
        //TODO
    }

    public List<Course> getCoursesByDifficultyRange(int min, int max) {
        //TODO
    }

    public List<String> getPopularCourseCodes(int minStudents) {
        //TODO
    }

    public Map<String, Integer> getTotalCreditsPerDepartment() {
        //TODO
    }

    public List<Course> getTop3HardestCourses() {
        //TODO
    }


    public Map<String, Double> getAverageDifficultyPerDepartment() {
        //TODO
    }


    public IntSummaryStatistics getEnrollmentStatistics() {
        //TODO
    }


    public University mergeFourSmallestDepartments() {
        //TODO
    }
}

public class UniversityTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Department> departments = new ArrayList<>();

        departments.add(new Department("Computer Science", Arrays.asList(
                new Course("SP", "Structural Programming", 6, 5, 1100),
                new Course("APS", "Algorithms", 6, 8, 500),
                new Course("OOP", "Object-Oriented Programming", 6, 7, 1000),
                new Course("NP", "Advanced Programming", 6, 9, 600)
        )));

        departments.add(new Department("Mathematics", Arrays.asList(
                new Course("CAL1", "Calculus I", 6, 6, 230),
                new Course("ALG", "Linear Algebra", 6, 7, 210),
                new Course("STAT", "Statistics", 6, 6, 170),
                new Course("CAL2", "Calculus II", 6, 8, 190)
        )));

        departments.add(new Department("Physics", Arrays.asList(
                new Course("PHY1", "Mechanics", 5, 7, 150),
                new Course("PHY2", "Electromagnetism", 5, 8, 140),
                new Course("PHY3", "Quantum Physics", 6, 9, 100),
                new Course("PHY4", "Optics", 4, 6, 130)
        )));

        departments.add(new Department("Chemistry", Arrays.asList(
                new Course("CH1", "Organic Chemistry", 6, 8, 120),
                new Course("CH2", "Inorganic Chemistry", 5, 7, 140),
                new Course("CH3", "Physical Chemistry", 6, 9, 110),
                new Course("CH4", "Analytical Chemistry", 5, 6, 130)
        )));

        departments.add(new Department("Biology", Arrays.asList(
                new Course("BIO1", "Cell Biology", 6, 7, 200),
                new Course("BIO2", "Genetics", 6, 8, 160),
                new Course("BIO3", "Ecology", 5, 6, 180),
                new Course("BIO4", "Biochemistry", 6, 9, 140)
        )));

        departments.add(new Department("Economics", Arrays.asList(
                new Course("MIC", "Microeconomics", 6, 6, 250),
                new Course("MAC", "Macroeconomics", 6, 7, 240),
                new Course("FIN", "Finance", 6, 8, 220),
                new Course("ACC", "Accounting", 6, 5, 260)
        )));

        departments.add(new Department("Languages", Arrays.asList(
                new Course("ENG", "English Language", 4, 4, 300),
                new Course("GER", "German Language", 4, 5, 180),
                new Course("FRE", "French Language", 4, 5, 160),
                new Course("ITA", "Italian Language", 4, 4, 170)
        )));

        departments.add(new Department("History", Arrays.asList(
                new Course("HIS1", "Ancient History", 5, 6, 140),
                new Course("HIS2", "Medieval History", 5, 7, 130),
                new Course("HIS3", "Modern History", 5, 6, 150),
                new Course("HIS4", "Contemporary History", 5, 7, 140)
        )));

        University university = new University(departments);

        String method = sc.nextLine().trim();

        System.out.println("Testing method: " + method);

        if (method.equals("getAllCourseNames")) {
            university.getAllCourseNames().forEach(System.out::println);

        } else if (method.equals("getCoursesWithMinCredits")) {
            int x = sc.nextInt();
            university.getCoursesWithMinCredits(x).forEach(System.out::println);

        } else if (method.equals("getTotalStudentCount")) {
            System.out.println(university.getTotalStudentCount());

        } else if (method.equals("getHardestCourse")) {
            System.out.println(university.getHardestCourse());

        } else if (method.equals("groupByDifficulty")) {
            university.groupByDifficulty().forEach((k, v) -> {
                System.out.println(String.format("Difficulty: %d", k));
                v.forEach(System.out::println);
            });

        } else if (method.equals("getCourseEnrollmentMap")) {
            university.getCourseEnrollmentMap().forEach((k, v) -> System.out.println(String.format("%s -> %d", k, v)));

        } else if (method.equals("getAverageEnrollmentPerCourse")) {
            System.out.println(university.getAverageEnrollmentPerCourse());

        } else if (method.equals("getSortedCourseCodes")) {
            university.getSortedCourseCodes().forEach(System.out::println);

        } else if (method.equals("getDepartmentToCourseNames")) {
            university.getDepartmentToCourseNames().forEach((k, v) -> {
                System.out.println(String.format("Department: %s", k));
                v.forEach(System.out::println);
            });

        } else if (method.equals("getAllCourses")) {
            university.getAllCourses().forEach(System.out::println);

        } else if (method.equals("getMostPopularDepartment")) {
            System.out.println(university.getMostPopularDepartment());

        } else if (method.equals("getStudentsByDifficulty")) {
            university.getStudentsByDifficulty().forEach((k, v) -> System.out.println(String.format("%s -> %d", k, v)));

        } else if (method.equals("getCoursesByDifficultyRange")) {
            int min = sc.nextInt();
            int max = sc.nextInt();
            university.getCoursesByDifficultyRange(min, max).forEach(System.out::println);

        } else if (method.equals("getPopularCourseCodes")) {
            int minStudents = sc.nextInt();
            university.getPopularCourseCodes(minStudents).forEach(System.out::println);

        } else if (method.equals("getTotalCreditsPerDepartment")) {
            university.getTotalCreditsPerDepartment().forEach((k, v) -> System.out.println(String.format("%s -> %d", k, v)));

        } else if (method.equals("getTop3HardestCourses")) {
            university.getTop3HardestCourses().forEach(System.out::println);

        } else if (method.equals("getAverageDifficultyPerDepartment")) {
            university.getAverageDifficultyPerDepartment().forEach((k, v) -> System.out.println(String.format("%s -> %.2f", k, v)));

        } else if (method.equals("getEnrollmentStatistics")) {
            System.out.println(university.getEnrollmentStatistics());

        } else if (method.equals("mergeFourSmallestDepartments")) {
            University updated = university.mergeFourSmallestDepartments();
            System.out.println("Merged University Departments:");
            updated.getDepartmentToCourseNames().forEach((k, v) -> {
                System.out.println(String.format("Department: %s", k));
                v.forEach(System.out::println);
            });

        } else {
            System.out.println("Unknown method!");
        }
    }
}

```

### Solution:
```java
package Lab.Lab6.Task1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

class Course {
    private String code;
    private String name;
    private int credits;
    private int difficulty;
    private int enrolledStudents;

    public Course(String code, String name, int credits, int difficulty, int enrolledStudents) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.difficulty = difficulty;
        this.enrolledStudents = enrolledStudents;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", code, name);
    }
}


class Department {
    private String name;
    private List<Course> courses;

    public Department(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return name;
    }
}


class University {

    private List<Department> departments;

    public University(List<Department> departments) {
        this.departments = departments;
    }


    public List<String> getAllCourseNames() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .map(Course::getName)
                .collect(Collectors.toList());
    }

    public List<Course> getCoursesWithMinCredits(int minCredits) {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .filter(c -> c.getCredits() == minCredits)
                .collect(Collectors.toList());
    }

    public int getTotalStudentCount() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .map(Course::getEnrolledStudents)
                .reduce(0, Integer::sum);
    }

    public Optional<Course> getHardestCourse() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .max(Comparator.comparingInt(Course::getDifficulty));
    }

    public Map<Integer, List<Course>> groupByDifficulty() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .collect(Collectors.groupingBy(
                        Course::getDifficulty,
                        Collectors.toList()
                ));
    }

    public Map<String, Integer> getCourseEnrollmentMap() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .collect(Collectors.toMap(
                        Course::getCode,
                        Course::getEnrolledStudents
                ));
    }

    public double getAverageEnrollmentPerCourse() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .mapToInt(Course::getEnrolledStudents)
                .average().orElse(0);
    }

    public List<String> getSortedCourseCodes() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .map(Course::getCode)
                .sorted()
                .collect(Collectors.toList());
    }

    public Map<String, List<String>> getDepartmentToCourseNames() {
        return departments.stream()
                .collect(Collectors.toMap(
                        Department::getName,
                        d -> d.getCourses().stream()
                                .map(Course::getName)
                                .collect(Collectors.toList())
                ));
    }

    public List<Course> getAllCourses() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .collect(Collectors.toList());
    }

    public Optional<Department> getMostPopularDepartment() {
        return departments.stream()
                .max(Comparator.comparingInt(
                        d -> d.getCourses().stream()
                                .mapToInt(Course::getEnrolledStudents)
                                .sum())
                );
    }

    public Map<Integer, Integer> getStudentsByDifficulty() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .collect(Collectors.groupingBy(
                        Course::getDifficulty,
                        Collectors.summingInt(Course::getEnrolledStudents)
                ));
    }

    public List<Course> getCoursesByDifficultyRange(int min, int max) {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .filter(c -> c.getDifficulty() >= min && c.getDifficulty() <= max)
                .collect(Collectors.toList());
    }

    public List<String> getPopularCourseCodes(int minStudents) {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .filter(c -> c.getEnrolledStudents() >= minStudents)
                .map(Course::getCode)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getTotalCreditsPerDepartment() {
        return departments.stream()
                .collect(Collectors.toMap(
                        Department::getName,
                        d -> d.getCourses()
                                .stream()
                                .mapToInt(Course::getCredits)
                                .sum()
                ));
    }

    public List<Course> getTop3HardestCourses() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .sorted(Comparator.comparingInt(Course::getDifficulty).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }


    public Map<String, Double> getAverageDifficultyPerDepartment() {
        return departments.stream()
                .collect(Collectors.toMap(
                        Department::getName,
                        d -> d.getCourses()
                                .stream()
                                .mapToInt(Course::getDifficulty)
                                .average()
                                .orElse(0)
                ));
    }


    public IntSummaryStatistics getEnrollmentStatistics() {
        return departments.stream()
                .flatMap(d -> d.getCourses().stream())
                .mapToInt(Course::getEnrolledStudents)
                .summaryStatistics();
    }


    public University mergeFourSmallestDepartments() {
        List<Department> smallest = departments.stream()
                .sorted(Comparator.comparingInt(
                        d -> d.getCourses()
                                .stream()
                                .mapToInt(Course::getEnrolledStudents)
                                .sum())
                )
                .limit(4)
                .collect(Collectors.toList());

        Department reduced = smallest.stream()
                .reduce((a, b) -> {
                    String mergedName = a.getName() + " & " + b.getName();
                    List<Course> mergedCourses = Stream.concat(
                                    a.getCourses().stream(),
                                    b.getCourses().stream()
                            )
                            .collect(Collectors.toList());
                    return new Department(mergedName, mergedCourses);
                }).orElse(null);

        List<Department> newDepartments = Stream.concat(
                        departments
                                .stream()
                                .filter(d -> !smallest.contains(d)),
                        Stream.of(reduced)
                )
                .collect(Collectors.toList());


        return new University(newDepartments);
    }
}

public class UniversityTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Department> departments = new ArrayList<>();

        departments.add(new Department("Computer Science", Arrays.asList(
                new Course("SP", "Structural Programming", 6, 5, 1100),
                new Course("APS", "Algorithms", 6, 8, 500),
                new Course("OOP", "Object-Oriented Programming", 6, 7, 1000),
                new Course("NP", "Advanced Programming", 6, 9, 600)
        )));

        departments.add(new Department("Mathematics", Arrays.asList(
                new Course("CAL1", "Calculus I", 6, 6, 230),
                new Course("ALG", "Linear Algebra", 6, 7, 210),
                new Course("STAT", "Statistics", 6, 6, 170),
                new Course("CAL2", "Calculus II", 6, 8, 190)
        )));

        departments.add(new Department("Physics", Arrays.asList(
                new Course("PHY1", "Mechanics", 5, 7, 150),
                new Course("PHY2", "Electromagnetism", 5, 8, 140),
                new Course("PHY3", "Quantum Physics", 6, 9, 100),
                new Course("PHY4", "Optics", 4, 6, 130)
        )));

        departments.add(new Department("Chemistry", Arrays.asList(
                new Course("CH1", "Organic Chemistry", 6, 8, 120),
                new Course("CH2", "Inorganic Chemistry", 5, 7, 140),
                new Course("CH3", "Physical Chemistry", 6, 9, 110),
                new Course("CH4", "Analytical Chemistry", 5, 6, 130)
        )));

        departments.add(new Department("Biology", Arrays.asList(
                new Course("BIO1", "Cell Biology", 6, 7, 200),
                new Course("BIO2", "Genetics", 6, 8, 160),
                new Course("BIO3", "Ecology", 5, 6, 180),
                new Course("BIO4", "Biochemistry", 6, 9, 140)
        )));

        departments.add(new Department("Economics", Arrays.asList(
                new Course("MIC", "Microeconomics", 6, 6, 250),
                new Course("MAC", "Macroeconomics", 6, 7, 240),
                new Course("FIN", "Finance", 6, 8, 220),
                new Course("ACC", "Accounting", 6, 5, 260)
        )));

        departments.add(new Department("Languages", Arrays.asList(
                new Course("ENG", "English Language", 4, 4, 300),
                new Course("GER", "German Language", 4, 5, 180),
                new Course("FRE", "French Language", 4, 5, 160),
                new Course("ITA", "Italian Language", 4, 4, 170)
        )));

        departments.add(new Department("History", Arrays.asList(
                new Course("HIS1", "Ancient History", 5, 6, 140),
                new Course("HIS2", "Medieval History", 5, 7, 130),
                new Course("HIS3", "Modern History", 5, 6, 150),
                new Course("HIS4", "Contemporary History", 5, 7, 140)
        )));

        University university = new University(departments);

        String method = sc.nextLine().trim();

        System.out.println("Testing method: " + method);

        if (method.equals("getAllCourseNames")) {
            university.getAllCourseNames().forEach(System.out::println);

        } else if (method.equals("getCoursesWithMinCredits")) {
            int x = sc.nextInt();
            university.getCoursesWithMinCredits(x).forEach(System.out::println);

        } else if (method.equals("getTotalStudentCount")) {
            System.out.println(university.getTotalStudentCount());

        } else if (method.equals("getHardestCourse")) {
            System.out.println(university.getHardestCourse());

        } else if (method.equals("groupByDifficulty")) {
            university.groupByDifficulty().forEach((k, v) -> {
                System.out.println(String.format("Difficulty: %d", k));
                v.forEach(System.out::println);
            });

        } else if (method.equals("getCourseEnrollmentMap")) {
            university.getCourseEnrollmentMap().forEach((k, v) -> System.out.println(String.format("%s -> %d", k, v)));

        } else if (method.equals("getAverageEnrollmentPerCourse")) {
            System.out.println(university.getAverageEnrollmentPerCourse());

        } else if (method.equals("getSortedCourseCodes")) {
            university.getSortedCourseCodes().forEach(System.out::println);

        } else if (method.equals("getDepartmentToCourseNames")) {
            university.getDepartmentToCourseNames().forEach((k, v) -> {
                System.out.println(String.format("Department: %s", k));
                v.forEach(System.out::println);
            });

        } else if (method.equals("getAllCourses")) {
            university.getAllCourses().forEach(System.out::println);

        } else if (method.equals("getMostPopularDepartment")) {
            System.out.println(university.getMostPopularDepartment());

        } else if (method.equals("getStudentsByDifficulty")) {
            university.getStudentsByDifficulty().forEach((k, v) -> System.out.println(String.format("%s -> %d", k, v)));

        } else if (method.equals("getCoursesByDifficultyRange")) {
            int min = sc.nextInt();
            int max = sc.nextInt();
            university.getCoursesByDifficultyRange(min, max).forEach(System.out::println);

        } else if (method.equals("getPopularCourseCodes")) {
            int minStudents = sc.nextInt();
            university.getPopularCourseCodes(minStudents).forEach(System.out::println);

        } else if (method.equals("getTotalCreditsPerDepartment")) {
            university.getTotalCreditsPerDepartment().forEach((k, v) -> System.out.println(String.format("%s -> %d", k, v)));

        } else if (method.equals("getTop3HardestCourses")) {
            university.getTop3HardestCourses().forEach(System.out::println);

        } else if (method.equals("getAverageDifficultyPerDepartment")) {
            university.getAverageDifficultyPerDepartment().forEach((k, v) -> System.out.println(String.format("%s -> %.2f", k, v)));

        } else if (method.equals("getEnrollmentStatistics")) {
            System.out.println(university.getEnrollmentStatistics());

        } else if (method.equals("mergeFourSmallestDepartments")) {
            University updated = university.mergeFourSmallestDepartments();
            System.out.println("Merged University Departments:");
            updated.getDepartmentToCourseNames().forEach((k, v) -> {
                System.out.println(String.format("Department: %s", k));
                v.forEach(System.out::println);
            });

        } else {
            System.out.println("Unknown method!");
        }
    }
}
```