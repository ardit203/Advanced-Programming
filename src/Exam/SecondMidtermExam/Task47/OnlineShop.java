package Exam.SecondMidtermExam.Task47;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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