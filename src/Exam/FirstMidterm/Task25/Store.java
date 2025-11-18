package Exam.FirstMidterm.Task25;

import java.util.List;

public class Store {
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
