package Exam.SecondMidtermExam.Task34;

import java.io.*;
import java.util.*;

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