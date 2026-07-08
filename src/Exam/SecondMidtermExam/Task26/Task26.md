You need to implement a class `FileSystem` for a simple file system.
For your file system you must implement your own class for a file `File` that stores:

* name (`String`)
* size (`Integer`)
* creation time (`LocalDateTime`)

The class must provide the following functionalities:

* `public void addFile(char folder, String name, int size, LocalDateTime createdAt)` – method for adding a new `File` into a folder with the given name (the folder name is a single character, it can be `.` or an uppercase letter).
* `public List<File> findAllHiddenFilesWithSizeLessThen(int size)` – returns a list of all hidden files (files whose name starts with the dot character `.`) with size smaller than `size`.
* `public int totalSizeOfFilesFromFolders(List<Character> folders)` – returns the total size of all files that are located in the folders given in the list `folders`.
* `public Map<Integer, Set<File>> byYear()` – returns a `Map` in which the files are grouped by the year of creation.
* `public Map<String, Long> sizeByMonthAndDay()` – returns a `Map` in which for each month and day (regardless of the year) the total size of all files created on that month and day is calculated. The month is obtained with `getMonth()`, and the day with `getDayOfMonth()`.

The files inside each folder are ordered by creation date in ascending order, then by name lexicographically, and finally by size.
Such a comparator must be implemented in the `File` class itself.

You should also implement a `toString` representation in the following format:

```
%-10[name] %5[size]B %[createdAt]
```

### Starter code
```java
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Partial exam II 2016/2017
 */
public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }
}
```

### Solution
```java
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key) ;
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }
}

class File implements Comparable<File> {
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

class Folder {
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

class FileSystem {
    private Map<Character, Folder> folders;

    public FileSystem() {
        this.folders = new HashMap<>();
    }


    public void addFile(char folder, String name, int size, LocalDateTime createdAt) {
        folders.computeIfAbsent(folder, k-> new Folder(folder)).addFile(name, size, createdAt);
    }

    public List<File> findAllHiddenFilesWithSizeLessThen(int size) {
        return folders.values()
                .stream()
                .flatMap(folder -> folder.getFiles().stream())
                .filter(file -> file.getName().startsWith(".") && file.getSize() < size)
                .collect(Collectors.toList());
    }

    public int totalSizeOfFilesFromFolders(List<Character> folderNames) {
        return folders.values()
                .stream()
                .filter(folder -> folderNames.contains(folder.getName()))
                .flatMap(folder -> folder.getFiles().stream())
                .mapToInt(File::getSize)
                .sum();
    }

    public Map<Integer, Set<File>> byYear() {
        return folders.values()
                .stream()
                .flatMap(folder -> folder.getFiles().stream())
                .collect(Collectors.groupingBy(
                        File::getYear,
                        TreeMap::new,
                        Collectors.toCollection(TreeSet::new)
                ));
    }

    public Map<String, Long> sizeByMonthAndDay() {
        return folders.values()
                .stream()
                .flatMap(folder -> folder.getFiles().stream())
                .collect(Collectors.groupingBy(
                        File::getMonthAndDay,
                        TreeMap::new,
                        Collectors.summingLong(File::getSize)
                ));
    }
}
```