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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class StoreFactory {
    public static Store create(String line){
        String [] tokens = line.split("\\s++");

        String name = tokens[0];
        List<Product> products = new ArrayList<>();
        for (int i = 1; i < tokens.length; i++) {
            String [] split = tokens[i].split(":");
            int discountPrice = Integer.parseInt(split[0]);
            int price = Integer.parseInt(split[1]);
            products.add(new Product(price, discountPrice));
        }
        return new Store(name, products.stream().sorted().collect(Collectors.toList()));
    }
}


class Store {
    private String name;
    private List<Product> products;

    public Store(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public double avgDiscount() {
        return products.stream().mapToInt(Product::getDiscountPercent).average().orElse(0);
    }

    public int absoluteDiscount() {
        return products.stream().mapToInt(Product::getAbsoluteDiscount).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append('\n');
        sb.append("Average discount: ").append(String.format("%.1f%%\n", avgDiscount()));
        sb.append("Total discount: ").append(absoluteDiscount()).append("\n");
        for (int i = 0; i < products.size(); i++) {
            sb.append(products.get(i));
            if (i < products.size() - 1) {
                sb.append('\n');
            }
        }

        return sb.toString();
    }
}



class Product implements Comparable<Product> {
    private int price;
    private int discountPrice;
    private int discountPercent;
    private int absoluteDiscount;

    public Product(int price, int discountPrice) {
        this.price = price;
        this.discountPrice = discountPrice;
        this.discountPercent = (price - discountPrice) * 100 / price;
        this.absoluteDiscount = price - discountPrice;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getAbsoluteDiscount() {
        return absoluteDiscount;
    }

    @Override
    public String toString() {
        return String.format("%2d%% %d/%d", discountPercent, discountPrice, price);
    }

    @Override
    public int compareTo(Product other) {
        return Comparator
                .comparingInt(Product::getDiscountPercent)
                .thenComparing(Product::getDiscountPrice)
                .compare(other, this);
    }
}


class Discounts {
    private List<Store> stores;

    public Discounts() {
        this.stores = new ArrayList<>();
    }

    public int readStores(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        stores = br.lines().map(StoreFactory::create).collect(Collectors.toList());

        return stores.size();
    }

    public List<Store> byAverageDiscount() {
        return stores.stream()
                .sorted(Comparator.comparingDouble(Store::avgDiscount).reversed().thenComparing(Store::getName))
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Store> byTotalDiscount(){
        return stores.stream()
                .sorted(Comparator.comparingInt(Store::absoluteDiscount).thenComparing(Store::getName))
                .limit(3)
                .collect(Collectors.toList());
    }


}

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