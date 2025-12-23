# **First Midterm Exam (+ composite design pattern)**

You need to define an application for a simple file system in which objects representing files/directories will be stored (objects that implement the interface `IFile`).

The interface `IFile` should be declared with the appropriate methods so that every file/directory has the following characteristics:

* it can access its name (`String getFileName()`)
* its size can be obtained (`long getFileSize()`)
* it can return a String representation of the file (`String getFileInfo(???)`)
* it can sort the collection of files inside it based on their sizes (`void sortBySize()`)
* it can find the largest file/directory inside it (`findLargestFile()`)

There are two types of file objects: `File` (regular file) and `Folder` (directory/folder). Both classes should implement the `IFile` interface.

A `File` stores information about its name and size (`long`).

The class `Folder` stores the same information as `File`, plus a list of files (both regular files and directories). For this class the following methods must be implemented:

* `void addFile(IFile file)` – a method that adds any file to the list.
* If a file with the same name already exists, the method should throw a `FileNameExistsException`, passing the conflicting file name.

Both classes must implement the methods declared in the `IFile` interface. The following must be respected:

* The size of a `Folder` is the sum of the sizes of all files (regular or directories) inside it.
* When generating the String representation for a directory, files and subdirectories inside that directory must be indented with a tab (`"\t"`).
* The String representation of a regular file is:
  `File name [file name left-padded to 10 characters] File size: [file size right-padded to 10 characters]`
* The String representation of a directory is:
  `Folder name [folder name left-padded to 10 characters] Folder size: [folder size right-padded to 10 characters]`
* Sorting is allowed only within the directory where the method is called, and files should be sorted by size in ascending order.
* The method `getLargestFile()` should return the largest regular file within the directory from which it is invoked.
* When `sortBySize()` is called on a directory, it should also call this method recursively on all its subdirectories.

A class `FileSystem` should be defined, which stores one root directory (`rootDirectory`). This class should implement:

* a default constructor `FileSystem()`
* `void addFile(IFile file)` – adds any file to the root directory
* `long findLargestFile()` – returns the size of the largest regular file within the root directory
* `void sortBySize()` – sorts all files (regular and directories) within the root directory by their sizes in ascending order


### Starter code
```java
public class FileSystemTest {

    public static Folder readFolder (Scanner sc)  {

        Folder folder = new Folder(sc.nextLine());
        int totalFiles = Integer.parseInt(sc.nextLine());

        for (int i=0;i<totalFiles;i++) {
            String line = sc.nextLine();

            if (line.startsWith("0")) {
                String fileInfo = sc.nextLine();
                String [] parts = fileInfo.split("\\s+");
                try {
                    folder.addFile(new File(parts[0], Long.parseLong(parts[1])));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                try {
                    folder.addFile(readFolder(sc));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return folder;
    }

    public static void main(String[] args)  {

        //file reading from input
        Scanner sc = new Scanner (System.in);

        System.out.println("===READING FILES FROM INPUT===");
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.addFile(readFolder(sc));
        } catch (FileNameExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("===PRINTING FILE SYSTEM INFO===");
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
        fileSystem.sortBySize();
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
        System.out.println(fileSystem.findLargestFile());
    }
}
```


### Solution
```java
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class FileSystemTest {

    public static Folder readFolder (Scanner sc)  {

        Folder folder = new Folder(sc.nextLine());
        int totalFiles = Integer.parseInt(sc.nextLine());

        for (int i=0;i<totalFiles;i++) {
            String line = sc.nextLine();

            if (line.startsWith("0")) {
                String fileInfo = sc.nextLine();
                String [] parts = fileInfo.split("\\s+");
                try {
                    folder.addFile(new File(parts[0], Long.parseLong(parts[1])));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                try {
                    folder.addFile(readFolder(sc));
                } catch (FileNameExistsException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return folder;
    }

    public static void main(String[] args)  {

        //file reading from input

        Scanner sc = new Scanner (System.in);

        System.out.println("===READING FILES FROM INPUT===");
        FileSystem fileSystem = new FileSystem();
        try {
            fileSystem.addFile(readFolder(sc));
        } catch (FileNameExistsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("===PRINTING FILE SYSTEM INFO===");
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING FILE SYSTEM INFO AFTER SORTING===");
        fileSystem.sortBySize();
        System.out.println(fileSystem.toString());

        System.out.println("===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===");
        System.out.println(fileSystem.findLargestFile());




    }
}

class FileNameExistsException extends RuntimeException {
    public FileNameExistsException(String file, String folder) {
        super(String.format("There is already a file named %s in the folder %s", file, folder));
    }
}


interface IFile {
    String getFileName();
    long getFileSize();
    String getFileInfo(int depth);
    void sortBySize();
    long findLargestFile();
}


class File implements IFile{
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


class Folder extends File {
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

class FileSystem {
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
```