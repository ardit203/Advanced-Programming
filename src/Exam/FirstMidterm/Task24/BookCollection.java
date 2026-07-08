package Exam.FirstMidterm.Task24;

import java.util.*;
import java.util.stream.Collectors;

public class BookCollection {
    private List<Book> books;

    public BookCollection() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void printByCategory(String category) {
        Comparator<Book> comparator = Comparator.comparing(Book::getTitle).thenComparing(Book::getPrice);
        books.stream()
                .filter(b -> b.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparator)))
                .forEach(System.out::println);

    }

    public List<Book> getCheapestN(int n) {
        return books.stream()
                .sorted(Comparator.comparing(Book::getPrice).thenComparing(Book::getTitle))
                .limit(n)
                .collect(Collectors.toList());
    }
}
