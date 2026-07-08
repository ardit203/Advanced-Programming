package Exam.SecondMidtermExam.Task26;

import java.time.LocalDateTime;
import java.util.Comparator;

public class File implements Comparable<File> {
    private String name;
    private int size;
    private LocalDateTime creationTime;

    public File(String name, int size, LocalDateTime creationTime) {
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public int getYear(){
        return creationTime.getYear();
    }

    public String getMonthAndDay(){
        return String.format("%s-%d", creationTime.getMonth(), creationTime.getDayOfMonth());
    }

    @Override
    public int compareTo(File other) {
        return Comparator.comparing(File::getCreationTime)
                .thenComparing(File::getName)
                .thenComparing(File::getSize)
                .compare(this,other);
    }

    @Override
    public String toString() {
        return String.format("%-10s %5dB %s", name, size, creationTime);
    }
}
