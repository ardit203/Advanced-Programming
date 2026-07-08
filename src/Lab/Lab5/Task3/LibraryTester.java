package Lab.Lab5.Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


class Book implements Comparable<Book> {
    private String isbn;
    private String title;
    private String author;
    private int year;
    private int copies = 0;
    private int totalBorrowers = 0;

    public Book(String isbn, String title, String author, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void addCopy() {
        copies++;
    }

    public void borrowBook() {
        totalBorrowers++;
        copies--;
    }

    public void returnBook() {
        addCopy();
    }

    public boolean isAvailable() {
        return copies > 0;
    }

    public int getTotalBorrowers() {
        return totalBorrowers;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        //isbn1 - “The Hobbit” by Goch (2025), available: 199, total borrows: 2
        return String.format("%s - %c%s%c by %s (%d), available: %d, total borrows: %d", isbn,'"', title, '"', author, year, copies, totalBorrowers);
    }

    @Override
    public int compareTo(Book o) {
        return Comparator.comparing(Book::getTotalBorrowers).reversed().thenComparing(Book::getYear).compare(this, o);
    }
}

class Member implements Comparable<Member> {
    private String id;
    private String name;
    private int borrowedNow = 0;
    private int totalBorrows = 0;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void borrowBook() {
        borrowedNow++;
        totalBorrows++;
    }

    public void returnBook() {
        borrowedNow--;
    }

    public int getTotalBorrows() {
        return totalBorrows;
    }

    public String getName() {
        return name;
    }

    public boolean isBorrower() {
        return borrowedNow > 0;
    }

    public String getId() {
        return id;
    }

    public int getBorrowedNow() {
        return borrowedNow;
    }

    @Override
    public String toString() {
        //Gorazd (id27) - borrowed now: 5, total borrows: 17
        return String.format("%s (%s) - borrowed now: %d, total borrows: %d", name, id, borrowedNow, totalBorrows);
    }

    @Override
    public int compareTo(Member o) {
        return Comparator.comparing(Member::getBorrowedNow).reversed().thenComparing(Member::getName).compare(this, o);
    }
}

class LibrarySystem {
    private String name;
    private Map<String, Member> members;
    private Map<String, Book> books;
    private Map<String, Set<String>> booksByMember;
    private Map<String, List<String>> queue;

    public LibrarySystem(String name) {
        this.name = name;
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        this.queue = new HashMap<>();
        this.booksByMember = new HashMap<>();
    }

    public void registerMember(String id, String fullName) {
        members.putIfAbsent(id, new Member(id, fullName));
    }

    public void addBook(String isbn, String title, String author, int year) {
        books.computeIfAbsent(isbn, k -> new Book(isbn, title, author, year)).addCopy();
    }

    public void borrowBook(String memberId, String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return;
        }

        if (!book.isAvailable()) {
            queue.computeIfAbsent(isbn, k -> new ArrayList<>()).add(memberId);
            return;
        }
        Member member = members.get(memberId);
        member.borrowBook();
        book.borrowBook();
        booksByMember.computeIfAbsent(isbn, k -> new HashSet<>()).add(memberId);
    }

    public void returnBook(String memberId, String isbn) {
        Member member = members.get(memberId);
        Book book = books.get(isbn);
        member.returnBook();
        book.returnBook();
        booksByMember.computeIfAbsent(isbn, k -> new HashSet<>()).remove(memberId);

        if (queue.isEmpty()) {
            return;
        }
        List<String> queueForIsbn = queue.computeIfAbsent(isbn, k -> new ArrayList<>());
        if(queueForIsbn.isEmpty()){
            return;
        }
        String id = queueForIsbn.remove(0);
        borrowBook(id, isbn);
    }

    public void printMembers() {
        members.values().stream().sorted().forEach(System.out::println);
    }

    public void printBooks() {
        books.values().stream().sorted().forEach(System.out::println);
    }

    public void printBookCurrentBorrowers(String isbn) {
        System.out.println(
                booksByMember.get(isbn)
                        .stream()
                        .sorted()
                        .collect(Collectors.joining(", "))
        );
    }

    public void printTopAuthors() {
        Comparator<Map.Entry<String,Integer>> comparator = Map.Entry.<String,Integer>comparingByValue(Comparator.reverseOrder())
                .thenComparing(Map.Entry::getKey);
        books.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.summingInt(Book::getTotalBorrowers)
                ))
                .entrySet()
                .stream()
                .sorted(comparator)
                .forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
    }
}

public class LibraryTester {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String libraryName = br.readLine();
            //   System.out.println(libraryName); //test
            if (libraryName == null) return;

            libraryName = libraryName.trim();
            LibrarySystem lib = new LibrarySystem(libraryName);

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals("END")) break;
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");

                switch (parts[0]) {

                    case "registerMember": {
                        lib.registerMember(parts[1], parts[2]);
                        break;
                    }

                    case "addBook": {
                        String isbn = parts[1];
                        String title = parts[2];
                        String author = parts[3];
                        int year = Integer.parseInt(parts[4]);
                        lib.addBook(isbn, title, author, year);
                        break;
                    }

                    case "borrowBook": {
                        lib.borrowBook(parts[1], parts[2]);
                        break;
                    }

                    case "returnBook": {
                        lib.returnBook(parts[1], parts[2]);
                        break;
                    }

                    case "printMembers": {
                        lib.printMembers();
                        break;
                    }

                    case "printBooks": {
                        lib.printBooks();
                        break;
                    }

                    case "printBookCurrentBorrowers": {
                        lib.printBookCurrentBorrowers(parts[1]);
                        break;
                    }

                    case "printTopAuthors": {
                        lib.printTopAuthors();
                        break;
                    }

                    default:
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
