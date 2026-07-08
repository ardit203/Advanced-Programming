package Exam.SecondMidtermExam.Task23;

public class Participant implements Comparable<Participant> {
    private String code;
    private String name;
    private int age;

    public Participant(String code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Participant other) {
        int compare1 = this.name.compareTo(other.name);
        if (compare1 == 0) return Integer.compare(this.age, other.age);
        return compare1;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d",code,name,age);
    }
}
