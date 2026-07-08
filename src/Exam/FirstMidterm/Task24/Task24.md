Write a class for a book `Book` in which the following are stored:

* title
* category
* price.

Implement a constructor with the following arguments
`Book(String title, String category, float price)`.

Then write a class `BookCollection` in which a collection of books is stored.
In this class the following methods should be implemented:

* `public void addBook(Book book)` – adds a book to the collection
* `public void printByCategory(String category)` – prints all books from the given category (the string is compared ignoring upper/lower case), sorted by the title of the book (if the title is the same, they are sorted by price)
* `public List<Book> getCheapestN(int n)` – returns a list of the N cheapest books (if there are fewer than N books in the collection, it returns all of them).

### Starter code
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class BooksTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		BookCollection booksCollection = new BookCollection();
		Set<String> categories = fillCollection(scanner, booksCollection);
		System.out.println("=== PRINT BY CATEGORY ===");
		for (String category : categories) {
			System.out.println("CATEGORY: " + category);
			booksCollection.printByCategory(category);
		}
		System.out.println("=== TOP N BY PRICE ===");
		print(booksCollection.getCheapestN(n));
	}

	static void print(List<Book> books) {
		for (Book book : books) {
			System.out.println(book);
		}
	}

	static TreeSet<String> fillCollection(Scanner scanner,
			BookCollection collection) {
		TreeSet<String> categories = new TreeSet<String>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(":");
			Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
			collection.addBook(book);
			categories.add(parts[1]);
		}
		return categories;
	}
}
```

### Solution
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.*;
import java.util.stream.Collectors;

public class BooksTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		BookCollection booksCollection = new BookCollection();
		Set<String> categories = fillCollection(scanner, booksCollection);
		System.out.println("=== PRINT BY CATEGORY ===");
		for (String category : categories) {
			System.out.println("CATEGORY: " + category);
			booksCollection.printByCategory(category);
		}
		System.out.println("=== TOP N BY PRICE ===");
		print(booksCollection.getCheapestN(n));
	}

	static void print(List<Book> books) {
		for (Book book : books) {
			System.out.println(book);
		}
	}

	static TreeSet<String> fillCollection(Scanner scanner,
			BookCollection collection) {
		TreeSet<String> categories = new TreeSet<String>();
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(":");
			Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
			collection.addBook(book);
			categories.add(parts[1]);
		}
		return categories;
	}
}

class Book {
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
        return String.format("%s (%s) %.2f", title, category, price);
    }
}

class BookCollection {
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
```