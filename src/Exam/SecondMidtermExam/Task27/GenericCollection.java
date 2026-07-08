package Exam.SecondMidtermExam.Task27;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GenericCollection<T extends Comparable<T> & IHasTimestamp> {
    private Map<String, Set<T>> elements;
    private final Comparator<T> reverseOrder = Comparator.reverseOrder();

    public GenericCollection() {
        this.elements = new HashMap<>();
    }

    public void addGenericItem(String category, T element) {
        elements.computeIfAbsent(category, k -> new HashSet<>()).add(element);
    }

    public Collection<T> findAllBetween(LocalDateTime from, LocalDateTime to) {
        return elements.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(e -> e.getTimestamp().isAfter(from) && e.getTimestamp().isBefore(to))
                .collect(Collectors.toCollection(() -> new TreeSet<>(reverseOrder)));
    }

    public Collection<T> itemsFromCategories(List<String> categories) {
        return elements.entrySet()
                .stream()
                .filter(e -> categories.contains(e.getKey()))
                .flatMap(e -> e.getValue().stream())
                .collect(Collectors.toCollection(() -> new TreeSet<>(reverseOrder)));
    }

    public Map<String, Set<T>> byMonthAndDay() {
        return elements.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        k -> String.format("%02d-%02d", k.getTimestamp().getMonthValue(), k.getTimestamp().getDayOfMonth()),
                        TreeMap::new,
                        Collectors.toCollection(() -> new TreeSet<>(reverseOrder))
                ));
    }

    public Map<Integer, Long> countByYear() {
        return elements.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        k -> k.getTimestamp().getYear(),
                        TreeMap::new,
                        Collectors.counting()
                ));
    }

}
