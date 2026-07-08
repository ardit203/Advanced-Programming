package Lab.Lab5.Task2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

	public static void main(String[] args) {
		findAll(System.in);
	}
    
    public static void findAll(InputStream inputStream) {
		Map<String, Set<String>> anagrams = new TreeMap<>();
        Map<String, String> keys = new HashMap<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        br.lines()
                .forEach(word -> {
                    String sorted = sortWord(word);
                    keys.putIfAbsent(sorted, word);
                    anagrams.computeIfAbsent(keys.get(sorted), k -> new LinkedHashSet<>()).add(word);
                });

        anagrams.values().forEach(words -> {
            System.out.println(String.join(" ", words));
        });
	}

    public static String sortWord(String word){
        String []split = word.split("");
        return Arrays.stream(split).sorted().collect(Collectors.joining());
    }
}
