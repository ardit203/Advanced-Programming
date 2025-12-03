# Lab exercise 4 - Advanced Programming


To develop a class `TermFrequency` that will count how many times each word appears in a given text (`String`). Letter case should **not** matter (uppercase and lowercase are treated the same), and punctuation symbols should be ignored (comma, period).

The class should have the following methods:

* `TermFrequency(InputStream inputStream, String[] stopWords)` – a constructor that receives the input stream from which the text should be read and an array of stop-words that should be ignored (not counted).

* `countTotal(): int` – returns the total number of words in the text.

* `countDistinct(): int` – returns the number of unique words.

* `mostOften(int k): List<String>` – returns a list containing the *k* words that appear most frequently in the text, ordered by number of occurrences from highest to lowest. If two words have the same frequency, they should be ordered alphabetically.

### Starter code:
```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class TermFrequencyTest {
	public static void main(String[] args) throws FileNotFoundException {
		String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
				"ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
				"што", "на", "а", "но", "кој", "ја" };
		TermFrequency tf = new TermFrequency(System.in,
				stop);
		System.out.println(tf.countTotal());
		System.out.println(tf.countDistinct());
		System.out.println(tf.mostOften(10));
	}
}
```

### Solution:
```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class TermFrequencyTest {
	public static void main(String[] args) throws FileNotFoundException {
		String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
				"ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
				"што", "на", "а", "но", "кој", "ја" };
		TermFrequency tf = new TermFrequency(System.in,
				stop);
		System.out.println(tf.countTotal());
		System.out.println(tf.countDistinct());
		System.out.println(tf.mostOften(10));
	}
}




class TermFrequency {
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
}
```