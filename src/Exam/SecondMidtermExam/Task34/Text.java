package Exam.SecondMidtermExam.Task34;

import java.util.Map;

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