package Exam.SecondMidtermExam.Task49;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Names {
    private Map<String, NameInfo> names;

    public Names() {
        this.names = new TreeMap<>();
    }


    public void addName(String name) {
        names.computeIfAbsent(name, k -> new NameInfo(name)).incrementOccurrences();
    }


    public void printN(int n) {
        names.entrySet()
                .stream()
                .filter(e -> e.getValue().getOccurrences() >= n)
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

    public String findName(int len, int x) {
        List<String> res = names.keySet()
                .stream()
                .filter(name -> name.length() < len)
                .collect(Collectors.toCollection(ArrayList::new));

        int index = 0;
        int count = 0;
        while (true) {
            if (index == res.size()) {
                index = 0;
            }
            if (count == x) {
                return res.get(index);
            }
            index++;
            count++;
        }
    }
}