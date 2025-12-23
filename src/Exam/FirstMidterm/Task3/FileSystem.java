package Exam.FirstMidterm.Task3;

public class FileSystem {
    private Folder root;

    public FileSystem(){
        root = new Folder("root");
    }

    public void addFile(IFile file){
        root.addFile(file);
    }

    public void sortBySize() {
        root.sortBySize();
    }

    public long findLargestFile() {
        return root.findLargestFile();
    }

    @Override
    public String toString() {
        return root.getFileInfo(0);
    }
}
