package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Course_and_Students;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class CourseDemo {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Course course = new Course("Software Engineering", 10);
        Supplier<Student> supplier = () -> {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s++");
            String index = parts[0];
            String name = parts[1];
            int grade = Integer.parseInt(parts[2]);
            float attendance = Float.parseFloat(parts[3]);
            return new Student(index, name, grade, attendance);
        };

        for (int i = 0; i < n; i++) {
            course.enroll(supplier);
        }
        //------------------------------------------------------------------------
        course.forEach(student -> System.out.println(student));

        //------------------------------------------------------------------------
        Predicate<Student> p1 = (student) -> student.getGrade() >= 6;
        Predicate<Student> p2 = (student) -> student.getAttendance() >= 70.0;

        course.filter(p1.and(p2));

        //------------------------------------------------------------------------

        course.findFirst((student) -> student.getGrade() >= 9);

        //------------------------------------------------------------------------

        course.mutate((student) -> student.setGrade(student.getGrade() + 1));

        //------------------------------------------------------------------------

        course.conditionalMutate(
                (student) -> student.getAttendance() >= 90.0,
                (student) -> student.setGrade(student.getGrade() + 1)
        );

        //--------------------------------------------------------------------------
        Function<Student, String> mapToLabels = (student) -> student.toString();
        System.out.println(
                Arrays.toString(
                        course.mapToLabels(mapToLabels)
                )
        );

        //-----------------------------------------------------------------------------------

        System.out.println(course);


    }
}
