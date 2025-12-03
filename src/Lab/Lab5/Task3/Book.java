package Lab.Lab5.Task3;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private int year;
    private int available;
    private int totalBorrows;

    public Book(String isbn, String title, String author, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = 0;
        this.totalBorrows = 0;
    }

    public void increment() {
        available++;
    }

    public void decrement() {
        if (available > 0) {
            available--;
        }
    }

    public void incrementTotal(){
        totalBorrows++;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getAvailable() {
        return available;
    }

    public int getTotalBorrows(){
        return totalBorrows;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("%s - \"%s\" by %s (%d), available: %d, total borrows: %d", isbn, title, author, year, available, totalBorrows);
    }
}
