Write a class `TextProcessor` for processing texts which will provide information about the distribution of words in the texts and their vector representation. For the class, implement:

* `TextProcessor()` – constructor
* `readText(InputStream is)` – method for reading text from an input stream. Each text is on a new line. From each text all unnecessary punctuation marks and digits should be removed, so that only words and spaces between them remain.
* `printTextsVectors(OutputStream os)` – method which, to an output stream, prints for each read text its vector representation, where the vector is of the form: `[frequency1, frequency2, ....]`.
  In the vector are the frequencies of all words (lexicographically sorted) that appeared in all texts read from the input stream. Letter case should be ignored.

    * Example: let the texts be `Napredno programiranje` and `napredno rabotenje`.
      Then the vector representation of the first text will be `[1, 1, 0]`, and of the second text `[1, 0, 1]`, because all words that appeared in all texts (lexicographically sorted) are: `[napredno, programiranje, rabotenje]`.
* `printCorpus(OutputStream os, int n, boolean ascending)` – method that prints the first n words from each text according to their frequency of occurrence. If the `ascending` argument is `true`, they are printed in ascending order, otherwise in descending order.
* `public void mostSimilarTexts(OutputStream os)` – method which prints to an output stream the two most similar texts obtained from the input stream. As a measure of similarity between texts, cosine similarity between the frequencies in the document vectors is used. The static function `cosineSimilarity` from the class `CosineSimilarityCalculator` should be used.

### Starter code
```java
class CosineSimilarityCalculator {
    public static double cosineSimilarity (Collection<Integer> c1, Collection<Integer> c2) {
        int [] array1;
        int [] array2;
        array1 = c1.stream().mapToInt(i -> i).toArray();
        array2 = c2.stream().mapToInt(i -> i).toArray();
        double up = 0.0;
        double down1=0, down2=0;

        for (int i=0;i<c1.size();i++) {
            up+=(array1[i] * array2[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down1+=(array1[i]*array1[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down2+=(array2[i]*array2[i]);
        }

        return up/(Math.sqrt(down1)*Math.sqrt(down2));
    }
}

public class TextProcessorTest {

    public static void main(String[] args) {
        TextProcessor textProcessor = new TextProcessor();

        textProcessor.readText(System.in);

        System.out.println("===PRINT VECTORS===");
        textProcessor.printTextsVectors(System.out);

        System.out.println("PRINT FIRST 20 WORDS SORTED ASCENDING BY FREQUENCY ");
        textProcessor.printCorpus(System.out,  20, true);

        System.out.println("PRINT FIRST 20 WORDS SORTED DESCENDING BY FREQUENCY");
        textProcessor.printCorpus(System.out, 20, false);

        System.out.println("===MOST SIMILAR TEXTS===");
        textProcessor.mostSimilarTexts(System.out);
    }
}
```

### Solution
```java
import java.io.*;
import java.util.*;

class CosineSimilarityCalculator {
    public static double cosineSimilarity(Collection<Integer> c1, Collection<Integer> c2) {
        int[] array1;
        int[] array2;
        array1 = c1.stream().mapToInt(i -> i).toArray();
        array2 = c2.stream().mapToInt(i -> i).toArray();
        double up = 0.0;
        double down1 = 0, down2 = 0;

        for (int i = 0; i < c1.size(); i++) {
            up += (array1[i] * array2[i]);
        }

        for (int i = 0; i < c1.size(); i++) {
            down1 += (array1[i] * array1[i]);
        }

        for (int i = 0; i < c1.size(); i++) {
            down2 += (array2[i] * array2[i]);
        }

        return up / (Math.sqrt(down1) * Math.sqrt(down2));
    }
}

class Text {
    private String rawText;
    private Map<String, Integer> words;

    public Text(String rawText, Map<String, Integer> words) {
        this.rawText = rawText;
        this.words = words;
    }

    public String getRawText() {
        return rawText;
    }

    public Map<String, Integer> getWords() {
        return words;
    }
    @Override
    public String toString() {
        return words.values().toString();
    }
}


class TextFactory {
    public static void createAndAddTextsAndWords(Map<String, Integer> words, List<Text> texts, String line) {
        String trimmedText = trim(line);
        String[] tokens = trimmedText.split("\\s++");
        Map<String, Integer> wordsInText = new TreeMap<>();

        for (String token : tokens) {
            words.merge(token, 1, Integer::sum);
            wordsInText.merge(token, 1, Integer::sum);
        }

        texts.add(new Text(line, wordsInText));
    }

    public static String trim(String str) {
        return str.replaceAll("[^A-Za-z\\s+]", "").toLowerCase();
    }
}

class TextProcessor {
    private Map<String, Integer> words;
    private List<Text> texts;

    public TextProcessor() {
        this.words = new TreeMap<>();
        this.texts = new ArrayList<>();
    }

    public void readText(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        br.lines()
                .forEach(line -> {
                    TextFactory.createAndAddTextsAndWords(words, texts, line);
                });
        computeVectors();
    }

    private void computeVectors() {//a text object contains the map words which has the word that the text contains
        // and the frequency of that word in that text with this we just put zeros for words that it does not contain
        words.keySet().forEach(k -> {
            texts.forEach(text -> {
                text.getWords().putIfAbsent(k, 0);
            });
        });
    }


    public void printTextsVectors(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        texts.forEach(pw::println);
        pw.flush();
    }

    public void printCorpus(OutputStream os, int n, boolean ascending) {
        PrintWriter pw = new PrintWriter(os);


        Comparator<Map.Entry<String, Integer>> comparator = ascending ?
                Map.Entry.comparingByValue() :
                Map.Entry.comparingByValue(Comparator.reverseOrder());

        words.entrySet()
                .stream()
                .sorted(comparator.thenComparing(Map.Entry.comparingByKey()))
                .limit(20)
                .forEach(e -> pw.println(String.format("%s : %d", e.getKey(), e.getValue())));

        pw.flush();
    }

    public void mostSimilarTexts(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        int iMax = 0;
        int jMax = 0;
        double max = 0;

        for (int i = 0; i < texts.size(); i++) {
            for (int j = i + 1; j < texts.size(); j++) {
                double similarity = CosineSimilarityCalculator.cosineSimilarity(texts.get(i).getWords().values(), texts.get(j).getWords().values());
                if(similarity > max){
                    max = similarity;
                    iMax = i;
                    jMax = j;
                }
            }
        }

        pw.println(texts.get(iMax).getRawText());
        pw.println(texts.get(jMax).getRawText());
        pw.println(String.format("%.10f", max));
        pw.flush();
    }
}

public class TextProcessorTest {

    public static void main(String[] args) {
        TextProcessor textProcessor = new TextProcessor();

        textProcessor.readText(System.in);

        System.out.println("===PRINT VECTORS===");
        textProcessor.printTextsVectors(System.out);

        System.out.println("PRINT FIRST 20 WORDS SORTED ASCENDING BY FREQUENCY ");
        textProcessor.printCorpus(System.out, 20, true);

        System.out.println("PRINT FIRST 20 WORDS SORTED DESCENDING BY FREQUENCY");
        textProcessor.printCorpus(System.out, 20, false);

        System.out.println("===MOST SIMILAR TEXTS===");
        textProcessor.mostSimilarTexts(System.out);
    }
}
```