package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static utils.StringNormalizer.normalizeLines;

public class FileManager {
    private String path;
    private int numFiles;
    public FileManager(String path) {
        this.path = path;
        this.numFiles = 0;
    }

    public boolean exists(){
        Path folderPath = Paths.get(path);
        if(Files.notExists(folderPath)){
            return false;
        }
        Path filePath = folderPath.resolve("1.txt");
        return Files.exists(filePath);
    }

    public boolean createFiles(List<String> tests) throws IOException {
        Path folderPath = Paths.get(path);
        Files.createDirectories(folderPath);
        this.numFiles = tests.size();
        for (int i = 0; i < tests.size(); i++) {
            String fileName = (i + 1) + ".txt";
            String content = tests.get(i);
            Path filePath = folderPath.resolve(fileName);
            if (Files.notExists(filePath)) {
                Files.writeString(filePath, content, StandardCharsets.UTF_8);
            } else {
                System.out.println("Skipping existing file: " + fileName);
            }
        }
        return true;
    }

    public InputOutput readFile(int i) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path + i + ".txt"));

        StringBuilder inputBuilder = new StringBuilder();
        StringBuilder outputBuilder = new StringBuilder();

        boolean inInput = false;
        boolean inOutput = false;

        for (String line : lines) {
            if (line.trim().equalsIgnoreCase("input:")) {
                inInput = true;
                inOutput = false;
                continue;
            }
            if (line.trim().equalsIgnoreCase("output:")) {
                inInput = false;
                inOutput = true;
                continue;
            }

            if (inInput) {
                inputBuilder.append(line).append("\n");
            } else if (inOutput) {
                outputBuilder.append(line).append("\n");
            }
        }


        String input = normalizeLines(inputBuilder.toString());
        String expectedOutput = normalizeLines(outputBuilder.toString());
        return new InputOutput(input, expectedOutput);
    }

    public int getNumFiles(){
        Path dir = Paths.get(path);

        try (Stream<Path> stream = Files.list(dir)) {
            return (int) stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
