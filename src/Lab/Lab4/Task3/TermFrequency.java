package Lab.Lab4.Task3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class TermFrequency {
    Map<String, Integer> words;
    Map<String, Integer> stopWords;

    public TermFrequency(InputStream is, String[] stopWords) {
        this.words = new HashMap<>();
        this.stopWords = new HashMap<>();
        read(is, Arrays.asList(stopWords));
    }

    private void read(InputStream is, List<String> stop) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        br.lines().forEach(line -> {
            for(String token : line.split("\\s+")) {
                String word = processString(token);

                if (word.isEmpty() || stop.contains(word)){
                    continue;
                }

                words.merge(word, 1, Integer::sum);
            }
        });
    }

    private String processString(String string) {
        return string.toLowerCase().replaceAll("[.,\\s]+", "");
//        return string.toLowerCase().replaceAll("[\"'.,„“”\\s]+", "");
    }



    public int countTotal() {
        return words.values().stream().mapToInt(i -> i).sum();
    }

    public int countDistinct() {
        return words.size();
    }

    public List<String> mostOften(int k) {
        List<String> result = new ArrayList<>(words.keySet());
        Comparator<String> comparator = Comparator.<String>comparingInt(words::get).reversed()
                .thenComparing(Comparator.naturalOrder());

        return result.stream().sorted(comparator).limit(k).collect(Collectors.toList());

    }

    //Additional Requirement

    public Map<Integer, List<String>> byFrequency(){
        Map<Integer, List<String>> result = new TreeMap<>(Comparator.reverseOrder());

        words.forEach((key, value) -> {
            result.computeIfAbsent(value, k -> new ArrayList<>()).add(key);
        });

        for (List<String> list : result.values()){
            Collections.sort(list);
        }

        return result;
    }

    public Set<String> stopWordsUsed(){
        return stopWords.keySet();
    }

    public String longestWord(){
        return words.keySet()
                .stream()
                .min(Comparator.comparing(String::length).reversed().thenComparing(Comparator.naturalOrder()))
                .orElse("");
    }
}
