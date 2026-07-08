package Lab.Lab4.Task3;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TermFrequencyTest {
    public static void main(String[] args) throws FileNotFoundException {
        String[] stop = new String[]{"во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја"};
        TermFrequency tf = new TermFrequency(System.in,
                stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));
    }
}


class TermFrequency {
    private Map<String, Integer> words;
    private List<String> stopWords;

    public TermFrequency(InputStream inputStream, String[] stopWords) {
        this.words = new HashMap<>();
        this.stopWords = Arrays.stream(stopWords).collect(Collectors.toList());
        readeLines(inputStream);
    }

    public void readeLines(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        br.lines().forEach(this::readLine);
    }

    public void readLine(String line) {
        String parsedLine = parse(line);

        String[] tokens = parsedLine.split("\\s+");

        for (String token : tokens) {
            if(token.isEmpty() || stopWords.contains(token)) continue;
            words.merge(token, 1, Integer::sum);
        }

    }

    public String parse(String str) {
        return str.toLowerCase().replaceAll("[.,]+", "");
    }

    public int countTotal(){
        return words.values().stream().mapToInt(i -> i).sum();
    }

    public int countDistinct(){
        return words.size();
    }

    public List<String> mostOften(int k){
        return words.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry::getKey))
                .limit(k)
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }
}