package Exam.SecondMidtermExam.Task26;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class Folder {
    private char name;
    private Set<File> files;

    public Folder(char name) {
        this.name = name;
        this.files = new TreeSet<>();
    }

    public void addFile(String name, int size, LocalDateTime createdAt) {
        files.add(new File(name, size, createdAt));
    }

    public char getName() {
        return name;
    }

    public Set<File> getFiles() {
        return files;
    }
}
