package Exam.FirstMidterm.Task3;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class File implements IFile{
    private String name;
    private long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    public File(String name) {
        this.name = name;
        this.size = 0;
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        return size;
    }

    @Override
    public String getFileInfo(int depth) {
        String dpt = IntStream.range(0, depth).mapToObj(i -> "\t").collect(Collectors.joining());
        return String.format("%sFile name: %10s File size: %10d\n", dpt, name, size);
    }

    @Override
    public void sortBySize() {
        return;
    }

    @Override
    public long findLargestFile() {
        return this.size;
    }
}
