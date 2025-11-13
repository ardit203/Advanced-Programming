package Exercises._04_Reading_From_SI_and_Comparator_Comparable.Book;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BooksTest {
    static void main() {
        BookCollection bookCollection = new BookCollection();
        Set<String> categories = reader(bookCollection, System.in);
        System.out.println("======PRINT BY CATEGORY======");
        categories.forEach(c -> {
            System.out.printf("Category: %s\n", c);
            bookCollection.printByCategory(c);
        });

        System.out.println("======N CHEAPEST BY PRICE======");
        bookCollection.getCheapestN(3).forEach(System.out::println);

    }

    public static Set<String> reader(BookCollection collection, InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        return br.lines().filter(Objects::nonNull).map(l -> {
            String[] tokens = l.split("\\s++");
            String title = tokens[0];
            String category = tokens[1];
            float price = Float.parseFloat(tokens[2]);

            collection.addBook(new Book(title, category, price));
            return category;
        }).collect(Collectors.toSet());
    }
}
