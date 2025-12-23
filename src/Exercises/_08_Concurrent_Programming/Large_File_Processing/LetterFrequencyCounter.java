package Exercises._08_Concurrent_Programming.Large_File_Processing;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LetterFrequencyCounter {

    // ============================================
    // Sequential letter counter
    // ============================================

    private static Map<Character, Long> countSequential(String text) {
        return text.chars()
                .mapToObj(i -> (char) i)
                .filter(Character::isLetter)
                .map(Character::toLowerCase)
                .collect(Collectors.groupingBy(
                        c -> c,
                        Collectors.counting()
                ));
    }


    // ============================================
    // Worker thread for parallel processing
    // ============================================

    public static class LetterFrequencyWorker extends Thread {
        private final String text;
        private Map<Character, Long> map;

        public LetterFrequencyWorker(String text) {
            this.text = text;
            this.map = new HashMap<>();
        }

        @Override
        public void run() {
            map = text.chars()
                    .mapToObj(i -> (char) i)
                    .filter(Character::isLetter)
                    .map(Character::toLowerCase)
                    .collect(Collectors.groupingBy(
                            c -> c,
                            Collectors.counting()
                    ));
        }

        public Map<Character, Long> getMap() {
            return map;
        }
    }

    // ============================================
    // Parallel letter counter
    // ============================================

    private static Map<Character, Long> countParallel(String text, int numThreads) throws InterruptedException {
        int textSize = text.length();
        int chunkSize = (int) Math.ceil((float) textSize / numThreads);


        List<LetterFrequencyWorker> workers = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = Math.min((i + 1) * chunkSize, textSize);
            workers.add(new LetterFrequencyWorker(text.substring(start, end)));
        }

        workers.forEach(LetterFrequencyWorker::start);

        for (LetterFrequencyWorker worker : workers) {
            worker.join();
        }

        Map<Character, Long> result = new HashMap<>();

        for (LetterFrequencyWorker worker : workers) {
            Map<Character, Long> map = worker.getMap();
            map.forEach((k, v) -> {
                result.merge(k, v, Long::sum);
            });
        }

        return result;
    }


    // ============================================
    // Helper → print histogram
    // ============================================
    public static void printCounts(int[] counts) {
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            System.out.printf("%c: %d%n", letter, counts[i]);
        }
    }

    // ============================================
    // MAIN — loads text, runs sequential & parallel
    // ============================================
    public static void main(String[] args) throws Exception {

        Path filePath = Paths.get("src/Exercises/_08_Concurrent_Programming/Large_File_Processing/large_text.txt");

        if (!Files.exists(filePath)) {
            System.err.println("File 'large_text.txt' not found!");
            return;
        }

        String text = Files.readString(filePath);

        System.out.println("Text length: " + text.length());
        System.out.println("---------------------------------------");

        // ====================================
        // Sequential timing
        // ====================================
        long startSeq = System.currentTimeMillis();
        Map<Character, Long> seqCounts = countSequential(text);
        long endSeq = System.currentTimeMillis();

        System.out.println("Sequential time: " + (endSeq - startSeq) + " ms");

        // ====================================
        // Parallel timing
        // ====================================

        int numThreads = Runtime.getRuntime().availableProcessors();

        long startPar = System.currentTimeMillis();
        Map<Character, Long> parCounts = countParallel(text, numThreads);
        long endPar = System.currentTimeMillis();

        System.out.println("Parallel time (" + numThreads + " threads): " + (endPar - startPar) + " ms");

        // ====================================
        // Compare results
        // ====================================
        boolean same = true;
        for (char i = 'a'; i <= 'z'; i++) {
            long seq = seqCounts.get(i);
            long par = parCounts.get(i);
            if (seq != par) {
                System.out.println(seq + " --- " + par);
                same = false;
                break;
            }
        }

        if (same)
            System.out.println("CORRECT: Sequential and parallel results match.");
        else
            System.out.println("ERROR: Results do NOT match!");

        System.out.println("---------------------------------------");
        System.out.println("Speedup: " + String.format("%.2f",
                (double) (endSeq - startSeq) / (endPar - startPar)) + "x");
    }


}