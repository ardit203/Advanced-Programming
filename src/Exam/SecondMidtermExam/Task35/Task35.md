Implement a class `WordVectors` for working with words and their representation in vectors. Implement the following methods:

* `public WordVectors(String[] words, List<List<Integer>> vectors)` – constructor for initialization with words and their corresponding representation in a vector of 5 integers (with values from 0–9). For each string from the array `words` there is a corresponding list of 5 integers (its vector representation).
* `public void readWords(List<String> words)` – a list of words is read from some text for which a vector representation needs to be calculated.
* `public List<Integer> slidingWindow(int n)` – calculates a vector representation of the read text (list of words) using a sliding window of size `n`. A window represents `n` neighboring words, starting from the first word (position 0) and includes the words from `0` to `(n - 1)`. Then this window is moved one position to the right, i.e. from `1` to `n`, etc. For each window of `n` elements, one scalar (integer) is obtained in such a way that the vectors of all words are summed and from the resulting vector the maximum value is taken. Example for the vectors of the words `quiz` and `attempt`:

`quiz = [1, 5, 7]` and `attempt = [3, 1, 4]` give the resulting vector `[1 + 3, 5 + 1, 7 + 4] = [4, 6, 11]` with maximum value `11`.

If for a certain word there is no vector representation, the neutral value `[5, 5, 5, 5, 5]` is used.

### Starter code
```java
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Word vectors test
 */
public class WordVectorsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] words = new String[n];
        List<List<Integer>> vectors = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            words[i] = parts[0];
            List<Integer> vector = Arrays.stream(parts[1].split(":"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            vectors.add(vector);
        }
        n = scanner.nextInt();
        scanner.nextLine();
        List<String> wordsList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            wordsList.add(scanner.nextLine());
        }
        WordVectors wordVectors = new WordVectors(words, vectors);
        wordVectors.readWords(wordsList);
        n = scanner.nextInt();
        List<Integer> result = wordVectors.slidingWindow(n);
        System.out.println(result.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")));
        scanner.close();
    }
}
```

### Solution
```java
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Vector {
    public static final Vector DEFAULT = new Vector(List.of(5, 5, 5, 5, 5));
    public static final Vector IDENTITY = new Vector(List.of(0, 0, 0, 0, 0));

    List<Integer> vector;

    public Vector(List<Integer> vector) {
        this.vector = vector;
    }

    public Vector sum(Vector other) {
        List<Integer> sum = IntStream.range(0, other.vector.size())
                .map(i -> vector.get(i) + other.vector.get(i))
                .boxed()
                .collect(Collectors.toList());

        return new Vector(sum);
    }

    public int max() {
        return vector.stream().mapToInt(v -> v).max().orElse(0);
    }
}


class WordVectors {
    private Map<String, Vector> wordVector;
    private List<Vector> vectors;

    public WordVectors(String[] words, List<List<Integer>> vectors) {
        this.wordVector = new TreeMap<>();
        IntStream.range(0, words.length)
                .forEach(i -> wordVector.put(words[i], new Vector(vectors.get(i))));
    }


    public void readWords(List<String> wordsList) {
        vectors = wordsList.stream()
                .map(word -> wordVector.getOrDefault(word, Vector.DEFAULT))
                .collect(Collectors.toList());
    }

    public List<Integer> slidingWindow(int n) {
        return IntStream.range(0, vectors.size() - n + 1)
                .mapToObj(i ->
                        vectors.subList(i, i + n)
                                .stream()
                                .reduce(Vector.IDENTITY, Vector::sum))
                .map(Vector::max)
                .collect(Collectors.toList());
    }
}

public class WordVectorsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] words = new String[n];
        List<List<Integer>> vectors = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            words[i] = parts[0];
            List<Integer> vector = Arrays.stream(parts[1].split(":"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            vectors.add(vector);
        }
        n = scanner.nextInt();
        scanner.nextLine();
        List<String> wordsList = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            wordsList.add(scanner.nextLine());
        }
        WordVectors wordVectors = new WordVectors(words, vectors);
        wordVectors.readWords(wordsList);
        n = scanner.nextInt();
        List<Integer> result = wordVectors.slidingWindow(n);
        System.out.println(result.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")));
        scanner.close();
    }
}
```