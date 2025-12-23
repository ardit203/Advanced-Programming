package Exam.FirstMidterm.Task3;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Folder extends File {
    private List<IFile> files;

    public Folder(String name){
        super(name);
        this.files = new ArrayList<>();
    }


    public void addFile(IFile file){
        int count = (int) files.stream()
                .filter(f -> f.getFileName().equalsIgnoreCase(file.getFileName()))
                .count();
        if(count > 0){
            throw new FileNameExistsException(file.getFileName(), this.getFileName());
        }
        files.add(file);
    }

    @Override
    public long getFileSize() {
        return files.stream()
                .mapToLong(IFile::getFileSize)
                .sum();
    }

    @Override
    public String getFileInfo(int depth) {
        String dpt = IntStream.range(0, depth).mapToObj(i -> "\t").collect(Collectors.joining());
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%sFolder name: %10s Folder size: %10d\n",dpt , this.getFileName(), this.getFileSize()));
        files.forEach(f -> sb.append(f.getFileInfo(depth + 1)));
        return sb.toString();
    }

    @Override
    public void sortBySize() {
        files = files.stream()
                .sorted(Comparator.comparingLong(IFile::getFileSize))
                .collect(Collectors.toList());
        files.forEach(IFile::sortBySize);
    }

    @Override
    public long findLargestFile() {
        return files.stream()
                .mapToLong(IFile::findLargestFile)
                .max()
                .orElse(0);
    }
}
