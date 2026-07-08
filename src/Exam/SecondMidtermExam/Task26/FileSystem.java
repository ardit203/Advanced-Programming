package Exam.SecondMidtermExam.Task26;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FileSystem {
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
