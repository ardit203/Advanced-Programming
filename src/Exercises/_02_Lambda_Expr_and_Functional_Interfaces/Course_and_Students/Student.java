package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Course_and_Students;

public class Student {
    private final String index;
    private String name;
    private int grade;
    private float attendance;

    public Student(String index, String name, int grade, float attendance) {
        this.index = index;
        this.name = name;
        this.grade = grade;
        this.attendance = attendance;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public float getAttendance() {
        return attendance;
    }

    public void setGrade(int grade) {
        if (grade > 10) {
            this.grade = 10;
            return;
        }
        if (grade < 5) {
            this.grade = 5;
            return;
        }
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %d - %f%%",index, name, grade, attendance);
    }
}
