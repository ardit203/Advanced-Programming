package Exam.FirstMidterm.Task25;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreFactory {
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
