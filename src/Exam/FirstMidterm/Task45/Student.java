package Exam.FirstMidterm.Task45;
import java.util.List;

public class Student {
    private String index;
    private List<Integer> points;
    private double totalPoints;

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
        this.totalPoints = points.stream().mapToInt(p -> p).sum() / 10.0;
    }

    public String getIndex() {
        return index;
    }

    public boolean hasSignature() {
        return points.size() >= 8;
    }

    public double totalPoints() {
        return totalPoints;
    }

    public int getYearOfStudy(){
        int year = 2000 + Integer.parseInt(index.substring(0,2));
        int now = 2020;
        return now - year;
    }


    @Override
    public String toString() {
        return String.format("%s %s %.2f", index, hasSignature() ? "YES" : "NO", totalPoints);
    }
}