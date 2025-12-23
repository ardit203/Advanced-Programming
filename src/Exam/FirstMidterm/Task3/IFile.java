package Exam.FirstMidterm.Task3;

public interface IFile {
    String getFileName();
    long getFileSize();
    String getFileInfo(int depth);
    void sortBySize();
    long findLargestFile();
}
