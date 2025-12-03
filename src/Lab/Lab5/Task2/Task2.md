# Lab Exercise 5 -Advanced Programming
## Task 2
Write a program that, from a given list of words (each word on a new line), will find the groups with five or more anagrams (an anagram is a word composed of the same letters). After finding the groups, they should be printed to standard output sorted in alphabetical order, with each group of anagrams on a new line, and the anagrams separated by a space (be careful not to have a trailing space at the end of the line). The order in which the groups of anagrams are printed corresponds to the order of the words that appeared in the input as the first representatives of the respective anagram group.

### Starter code:
```java
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Anagrams {

	public static void main(String[] args) {
		findAll(System.in);
	}
    
    public static void findAll(InputStream inputStream) {
		
	}
}
```

### Solution:
```java
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
```