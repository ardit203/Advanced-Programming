package Lab.Lab5.Task3;

import java.util.*;
import java.util.stream.Collectors;

public class LibrarySystem {
    private String name;
    private Map<String, Book> books;
    private Map<String, Member> members;
    private Map<String, List<String>> waiting;
    private Map<String, List<String>> currentBorrowers;

    public LibrarySystem(String name) {
        this.name = name;
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.waiting = new HashMap<>();
        this.currentBorrowers = new HashMap<>();
    }

    public void registerMember(String id, String fullName) {
        members.put(id, new Member(id, fullName));
    }

    public void addBook(String isbn, String title, String author, int year) {
        books.computeIfAbsent(isbn, k -> new Book(isbn, title, author, year)).increment();
    }

    public void borrowBook(String memberId, String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return;
        }

        if (book.getAvailable() == 0) {
            waiting.computeIfAbsent(isbn, k -> new ArrayList<>()).add(memberId);
            return;
        }

        Member member = members.get(memberId);

        member.increment();
        member.incrementTotal();
        book.decrement();
        book.incrementTotal();
        currentBorrowers.computeIfAbsent(isbn, k -> new ArrayList<>()).add(memberId);
    }

    public void returnBook(String memberId, String isbn) {
        Book book = books.get(isbn);
        Member member = members.get(memberId);

        book.increment();
        member.decrement();

        currentBorrowers.computeIfAbsent(isbn, k -> new ArrayList<>()).remove(memberId);


        List<String> waitingList = waiting.get(isbn);
        if (waitingList != null && !waitingList.isEmpty()) {
            String id = waitingList.remove(0);
            Member waitingMember = members.get(id);
            waitingMember.increment();
            waitingMember.incrementTotal();
            book.decrement();
            book.incrementTotal();
            currentBorrowers.computeIfAbsent(isbn, k -> new ArrayList<>()).add(id);
        }

    }

    public void printMembers() {
        members.values()
                .stream()
                .sorted(Comparator.comparing(Member::getBorrowed).reversed().thenComparing(Member::getName))
                .forEach(System.out::println);
    }

    public void printBooks() {
        books.values()
                .stream()
                .sorted(
                        Comparator.comparing(Book::getTotalBorrows)
                                .reversed()
                                .thenComparing(Book::getYear)
                )
                .forEach(System.out::println);
    }

    public void printBookCurrentBorrowers(String isbn) {
        List<String> borrowedNow = currentBorrowers.get(isbn);
        if (borrowedNow == null || borrowedNow.isEmpty()) {
            return;
        }
        String result = borrowedNow
                .stream()
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }

    public void printTopAuthors() {
        books.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.summingInt(Book::getTotalBorrows)
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt((Map.Entry<String, Integer> e) -> e.getValue()).reversed().thenComparing(Map.Entry::getKey))
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
    }


    //Additional Requirement
    public Map<Book, Integer> getBooksAndNumberOfBorrowings() {
        Map<Book, Integer> result = new TreeMap<>(Comparator.comparing(Book::getTitle));
        books.forEach((k, v) -> result.put(v, v.getTotalBorrows()));
        return result;
    }

    public Map<String, TreeSet<String>> getAuthorsWithBooks(){
        return books.values().stream().collect(Collectors.groupingBy(
                Book::getAuthor,
                Collectors.mapping(
                        Book::getIsbn,
                        Collectors.toCollection(TreeSet::new)
                )
        ));
    }
}
