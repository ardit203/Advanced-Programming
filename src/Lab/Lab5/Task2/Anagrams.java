package Lab.Lab5.Task2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    public static void main(String[] args) {
        findAll(System.in);
    }

    public static void findAll(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        Map<String, List<String>> anagrams = new TreeMap<>();
        Map<String, String> keys = new HashMap<>();
        br.lines().forEach(w -> mapper(w, anagrams, keys));

        anagrams.values().stream()
                .filter(v -> v.size() >= 5)
                .forEach(l -> System.out.println(String.join(" ", l)));
    }

    public static void mapper(String word, Map<String, List<String>> anagrams, Map<String, String> keys) {
        String sorted = sorted(word);

        keys.putIfAbsent(sorted, word);

        anagrams.computeIfAbsent(keys.get(sorted), k -> new ArrayList<>()).add(word);
    }

    public static String sorted(String str) {
        return Arrays.stream(str.split("")).sorted().collect(Collectors.joining());
    }
}
