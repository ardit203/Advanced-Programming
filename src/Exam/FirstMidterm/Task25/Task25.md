You need to implement the class **`Discounts`** for processing information about prices and discounted prices of certain products in several stores (objects of class **`Store`**). You need to implement the following methods:

* **`public int readStores(InputStream inputStream)`** – a method for reading the data for the stores and the prices of the products.
  The data for each store is given in a separate line in the format
  **[`name`] [`discount_price1:price1`] [`discount_price2:price2`] ...** (see the example).
  The method returns how many stores were read.

* **`public List<Store> byAverageDiscount()`** – a method that returns a list of the **top 3 stores** with the highest *average discount* (the average percentage discount across all products in that store).
  The discount (the reduction of the price) is expressed as an integer (percentage) and should be calculated from the discounted price and the original price.
  If two stores have the same discount, they should be ordered lexicographically by name.

* **`public List<Store> byTotalDiscount()`** – a method that returns a list of the **top 3 stores** with the highest *total discount* (the sum of the absolute discount of all products).
  The absolute discount is the difference between the original price and the discounted price.
  If two stores have the same discount, they should be ordered lexicographically by name.

---

Additionally, in the class **`Store`**, you need to implement string representation, i.e., the method:

```java
public String toString()
```

which returns a representation in the following format:

```
[Store_name]  
Average discount: [rounded value to one decimal place]%  
Total discount: [total absolute discount]  
[percentage with two decimals] [discounted_price]/[original_price]  
...
```

The products should be sorted by the percentage discount
(if equal, then by absolute discount) in **descending order**.

See the example for details.

### Starter code
```java
/**
 * Discounts
 */
public class DiscountsTest {
    public static void main(String[] args) {
        Discounts discounts = new Discounts();
        int stores = discounts.readStores(System.in);
        System.out.println("Stores read: " + stores);
        System.out.println("=== By average discount ===");
        discounts.byAverageDiscount().forEach(System.out::println);
        System.out.println("=== By total discount ===");
        discounts.byTotalDiscount().forEach(System.out::println);
    }
}
```


### Solution
```java
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountsTest {
    public static void main(String[] args) throws IOException {
        Discounts discounts = new Discounts();
        int stores = discounts.readStores(System.in);
        System.out.println("Stores read: " + stores);
        System.out.println("=== By average discount ===");
        discounts.byAverageDiscount().forEach(System.out::println);
        System.out.println("=== By total discount ===");
        discounts.byTotalDiscount().forEach(System.out::println);
    }
}

class Item {
    private int price;
    private int discountPrice;
    private int percentage;
    private int absoluteDiscount;

    public Item(int price, int discountPrice) {
        this.price = price;
        this.discountPrice = discountPrice;
        this.absoluteDiscount = this.price - this.discountPrice;
        this.percentage = calculatePercentage();
    }

    private int calculatePercentage() {
        double result = (1 - discountPrice * 1.0 / price) * 100;
        return (int) result;
    }


    public int getPrice() {
        return price;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getAbsoluteDiscount() {
        return absoluteDiscount;
    }

    @Override
    public String toString() {
        return String.format("%2d%% %d/%d", percentage, discountPrice, price);
    }

    public static Item createItem(String line) {
        String[] tokens = line.split(":");
        int discountPrice = Integer.parseInt(tokens[0]);
        int price = Integer.parseInt(tokens[1]);
        return new Item(price, discountPrice);
    }
}

class Store {
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

class Discounts {
    private List<Store> stores;

    public Discounts() {
        this.stores = new ArrayList<>();
    }

    public int readStores(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        int count = 0;

        while ((line = br.readLine()) != null){
            stores.add(Store.createStore(line));
            count++;
        }
        return count;
    }

    public List<Store> byAverageDiscount() {
        return stores.stream()
                .sorted(Comparator.comparingDouble(Store::getAverageDiscount).reversed().thenComparing(Store::getName))
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Store> byTotalDiscount() {
        return stores.stream()
                .sorted(Comparator.comparingInt(Store::getAbsoluteDiscount).thenComparing(Store::getName))
                .limit(3)
                .collect(Collectors.toList());
    }
}
```