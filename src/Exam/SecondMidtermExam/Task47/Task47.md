Implement an application for storing products in an online shop. For that purpose, define a class `OnlineShop` in which you will store all products in the shops and which will offer functionalities for listing the products and buying them. For the class, implement:

* Default constructor `OnlineShop()`
* Method `void addProduct(String category, String id, String name, LocalDateTime createdAt, double price)` – method for adding a product to the online shop. Each product is defined with a category, ID, name, the date when it is added to the shop, and its price.
* Method `double buyProduct(String id, int quantity)` – which will implement the purchase of `quantity` units of the product with ID `id`. The method should return how much money is spent for this transaction. An exception of type `ProductNotFoundException` should be thrown if the product does not exist. The method must have complexity `O(1)`.
* Method `List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize)` which will list all products from the category `category`, sorted according to the comparator `comparatorType`, grouped into pages of size `pageSize` (pagination). `category` may also be `null`, in which case all products in the online shop are listed.

`COMPARATOR_TYPE` is an enum that is given to you in the starter code. For printing the products, use the built-in `toString` notation in the IDE (preserve the order and names of the variables).

### Starter code
```java
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String message) {
        super(message);
    }
}


class Product {
    
}


class OnlineShop {
    

    OnlineShop() {
        
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price){
        
    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException{
        throw new ProductNotFoundException("");
        //return 0.0;
    }

    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        List<List<Product>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        return result;
    }

}

public class OnlineShopTest {

    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        double totalAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equalsIgnoreCase("addproduct")) {
                String category = parts[1];
                String id = parts[2];
                String name = parts[3];
                LocalDateTime createdAt = LocalDateTime.parse(parts[4]);
                double price = Double.parseDouble(parts[5]);
                onlineShop.addProduct(category, id, name, createdAt, price);
            } else if (parts[0].equalsIgnoreCase("buyproduct")) {
                String id = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                try {
                    totalAmount += onlineShop.buyProduct(id, quantity);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String category = parts[1];
                if (category.equalsIgnoreCase("null"))
                    category=null;
                String comparatorString = parts[2];
                int pageSize = Integer.parseInt(parts[3]);
                COMPARATOR_TYPE comparatorType = COMPARATOR_TYPE.valueOf(comparatorString);
                printPages(onlineShop.listProducts(category, comparatorType, pageSize));
            }
        }
        System.out.println("Total revenue of the online shop is: " + totalAmount);

    }

    private static void printPages(List<List<Product>> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println("PAGE " + (i + 1));
            listProducts.get(i).forEach(System.out::println);
        }
    }
}
```

### Solution
```java
package Exam.SecondMidtermExam.Task47;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String id) {
        super(String.format("Product with id %s does not exist in the online shop!", id));
    }
}


class ComparatorFactory {
    public static Comparator<Product> OLDEST_FIRST = Comparator.comparing(Product::getCreatedAt);
    //    public static Comparator<Product> NEWEST_FIRST = OLDEST_FIRST.reversed();
    public static Comparator<Product> LOWEST_PRICE_FIRST = Comparator.comparing(Product::getPrice);
    //    public static Comparator<Product> HIGHEST_PICE_FIRST = LOWEST_PRICE_FIRST.reversed();
    public static Comparator<Product> LEAST_SOLD_FIRST = Comparator.comparing(Product::getSales);
//    public static Comparator<Product> MOST_SOLD_FIRST = LEAST_SOLD_FIRST.reversed();

    public static Comparator<Product> getComparator(COMPARATOR_TYPE type) {
        switch (type) {
            case NEWEST_FIRST:
                return OLDEST_FIRST.reversed();
            case OLDEST_FIRST:
                return OLDEST_FIRST;
            case LOWEST_PRICE_FIRST:
                return LOWEST_PRICE_FIRST;
            case HIGHEST_PRICE_FIRST:
                return LOWEST_PRICE_FIRST.reversed();
            case MOST_SOLD_FIRST:
                return LEAST_SOLD_FIRST.reversed();
            case LEAST_SOLD_FIRST:
                return LEAST_SOLD_FIRST;
            default:
                throw new RuntimeException("NO COMPARATOR WAS GIVEN");
        }
    }
}


class Product {
    private String category;
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private double price;
    private int sales;

    public Product(String category, String id, String name, LocalDateTime createdAt, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        this.sales = 0;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getPrice() {
        return price;
    }

    public int getSales() {
        return sales;
    }

    public void incrementSales(int quantity) {
        sales += quantity;
    }

//    @Override
//    public String toString() {
//        //Product{id='050be27b', name='product0', createdAt=2019-01-14T23:17:46.715710, price=2913.14, quantitySold=14}
//        return String.format("Product{id='%s', name='%s', createdAt=%s, price=%f, quantitySold=%d}",
//                id, name, createdAt, price, sales);
//    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold=" + sales +
                '}';
    }
}


class OnlineShop {
    private Map<String, Product> products;
    private Map<String, Set<Product>> productsByCategory;

    OnlineShop() {
        this.products = new HashMap<>();
        this.productsByCategory = new HashMap<>();
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price) {
        Product product = new Product(category, id, name, createdAt, price);
        products.putIfAbsent(id, product);
        productsByCategory.computeIfAbsent(category, k -> new HashSet<>()).add(product);
    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException {
        Product product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        product.incrementSales(quantity);
        return product.getPrice() * quantity;
    }

    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        Comparator<Product> comparator = ComparatorFactory.getComparator(comparatorType);

        Stream<Product> productStream = category != null
                ? productsByCategory.get(category).stream()
                : products.values().stream();


        List<Product> allProducts = productStream.sorted(comparator).collect(Collectors.toList());
//        List<List<Product>> result = new ArrayList<>();
//        int index = 0;
//        while (index < allProducts.size()) {
//            int startIndex = index;
//            int endIndex = Math.min(allProducts.size(), index + pageSize);
//
//            result.add(allProducts.subList(startIndex, endIndex));
//            index = endIndex;
//        }
//
//        return result;

        return IntStream.iterate(0, i -> i < allProducts.size(), i -> i + pageSize)
                .mapToObj(i -> allProducts.subList(i, Math.min(i + pageSize, allProducts.size())))
                .collect(Collectors.toList());
    }

}

public class OnlineShopTest {

    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        double totalAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equalsIgnoreCase("addproduct")) {
                String category = parts[1];
                String id = parts[2];
                String name = parts[3];
                LocalDateTime createdAt = LocalDateTime.parse(parts[4]);
                double price = Double.parseDouble(parts[5]);
                onlineShop.addProduct(category, id, name, createdAt, price);
            } else if (parts[0].equalsIgnoreCase("buyproduct")) {
                String id = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                try {
                    totalAmount += onlineShop.buyProduct(id, quantity);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String category = parts[1];
                if (category.equalsIgnoreCase("null"))
                    category = null;
                String comparatorString = parts[2];
                int pageSize = Integer.parseInt(parts[3]);
                COMPARATOR_TYPE comparatorType = COMPARATOR_TYPE.valueOf(comparatorString);
                printPages(onlineShop.listProducts(category, comparatorType, pageSize));
            }
        }
        System.out.println("Total revenue of the online shop is: " + totalAmount);

    }

    private static void printPages(List<List<Product>> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println("PAGE " + (i + 1));
            listProducts.get(i).forEach(System.out::println);
        }
    }
}
```