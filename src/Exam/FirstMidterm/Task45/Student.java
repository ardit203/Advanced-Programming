package Exam.FirstMidterm.Task45;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String index;
    private List<Integer> points;

    public Student(String index, List<Integer> points) {
        this.index = index;
        this.points = points;
    }


    public String getIndex() {
        return index;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public boolean hasNoSignature() {
        return points.size() < 8;
    }

    public boolean hasSignature() {
        return points.size() >= 8;
    }

    public double getTotalPoints() {
        return points.stream().mapToDouble(p -> p).sum() / 10.0;
    }

    public int getYear(){
        return 2020 - (2000 + Integer.parseInt(index.substring(0,2)));
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", index, hasSignature() ? "YES" : "NO", getTotalPoints());
    }
}
