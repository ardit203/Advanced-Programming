package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Book;

public class Book {
    private String title;
    private String category;
    private float price;

    public Book(String title, String category, float price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Category: %s, Price: %.2f", title, category, price);
    }
}
