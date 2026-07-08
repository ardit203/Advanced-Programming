package Exam.SecondMidtermExam.Task49;

import java.util.HashSet;
import java.util.Set;

public class NameInfo {
    private int occurrences;
    private Set<Character> uniqueLetters;

    public NameInfo(String name) {
        occurrences = 0;
        uniqueLetters = new HashSet<>();
        for (char c : name.toCharArray()) {
            uniqueLetters.add(Character.toLowerCase(c));
        }
    }

    public int getOccurrences() {
        return occurrences;
    }

    public Set<Character> getUniqueLetters() {
        return uniqueLetters;
    }

    public int numUniqueLetters() {
        return uniqueLetters.size();
    }

    public void incrementOccurrences() {
        occurrences++;
    }

    @Override
    public String toString() {
        return String.format("(%d) %d", occurrences, numUniqueLetters());
    }
}