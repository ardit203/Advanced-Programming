package Exam.FirstMidterm.Task25;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Store {
    private String name;
    private List<Item> items;

    public Store(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public double getAverageDiscount(){
        return items.stream()
                .mapToInt(Item::getPercentage)
                .average()
                .orElse(0);
    }

    public int getAbsoluteDiscount(){
        return items.stream()
                .mapToInt(Item::getAbsoluteDiscount)
                .sum();
    }

    @Override
    public String toString() {
        String itemsText = items.stream()
                .sorted(Comparator.comparingInt(Item::getPercentage)
                        .thenComparingInt(Item::getAbsoluteDiscount)
                        .reversed())
                .map(Item::toString)
                .collect(Collectors.joining("\n"));

        return String.format("%s\nAverage discount: %.1f%%\nTotal discount: %d\n%s",
                name, getAverageDiscount(), getAbsoluteDiscount(), itemsText
        );
    }

    public static Store createStore(String line) {
        String[] tokens = line.split("\\s++");
        String name = tokens[0];
        List<Item> itemsList = new ArrayList<>();

        IntStream.range(1, tokens.length)
                .forEach(i -> {
                    itemsList.add(Item.createItem(tokens[i]));
                });

        return new Store(name, itemsList);
    }
}
