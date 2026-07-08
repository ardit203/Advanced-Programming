package Exam.SecondMidtermExam.Task48;

import java.util.*;

public class Distribution implements Comparable<Distribution> {
    String program;
    Map<Integer, Integer> distributions;

    public Distribution(Map.Entry<String, Set<Student>> entry) {
        this.program = entry.getKey();
        this.distributions = new TreeMap<>();
        mapper(entry.getValue());
    }

    public void mapper(Set<Student> students) {
        for (int i = 6; i <= 10; i++) {
            int index = i;
            int count = (int) students
                    .stream()
                    .flatMap(s -> s.getGrades().stream()).filter(g -> g == index)
                    .count();
            distributions.put(i, count);
        }
    }

    @Override
    public int compareTo(Distribution o) {
        return Integer.compare(o.distributions.get(10), distributions.get(10));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(program).append("\n");
        distributions.forEach((k, v) -> {
            int c = (int) Math.ceil(v / 10.0);
            String astr = "*".repeat(c);
            sb.append(String.format("%2d | %s(%d)\n", k, astr, v));
        });
        return sb.toString();
    }
}