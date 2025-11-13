package Exam.FirstMidterm.Task3;

public interface IFile {
    public String getFileName();
    public long getFileSize();
    public String getFileInfo();
    public void sortBySize();
    public IFile findLargestFile();
}
