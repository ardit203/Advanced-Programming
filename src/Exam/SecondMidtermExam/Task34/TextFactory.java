package Exam.SecondMidtermExam.Task34;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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