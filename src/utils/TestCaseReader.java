package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class TestCaseReader {
    public String input;
    public String expectedOutput;
    public String actualOutput;


    public void readFile(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        StringBuilder inputBuilder = new StringBuilder();
        StringBuilder outputBuilder = new StringBuilder();

        boolean inInput = false;
        boolean inOutput = false;

        for (String line : lines) {
            if (line.trim().equalsIgnoreCase("Input:")) {
                inInput = true;
                inOutput = false;
                continue;
            }
            if (line.trim().equalsIgnoreCase("Output:")) {
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


        input = normalizeLines(inputBuilder.toString());
        expectedOutput = normalizeLines(outputBuilder.toString());
    }


    public void test(Runnable mainMethod, String path) throws IOException {
        readFile(Path.of(path));

        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            ByteArrayInputStream testIn =
                    new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
            System.setIn(testIn);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream testOut = new PrintStream(baos, true, StandardCharsets.UTF_8);
            System.setOut(testOut);

            mainMethod.run();

            testOut.flush();
            actualOutput = normalizeLines(baos.toString(StandardCharsets.UTF_8));


        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }


    private String normalizeLines(String s) {
        if (s == null) return "";

        String result = s
                .replace("\r\n", "\n")
                .replace("\r", "\n");

        // remove literal "\t"
        result = result.replaceAll("\\\\t", "\t");

        // remove trailing spaces / real tabs at end of each line
        result = result.replaceAll("(?m)[ \\t]+$", "");
        // remove trailing newlines at the END of the string
        result = result.replaceAll("\n+$", "");

        return result;
    }


}
