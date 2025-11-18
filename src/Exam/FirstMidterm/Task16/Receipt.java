package Exam.FirstMidterm.Task16;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private int id;
    private List<Product> products;

    public Receipt(int id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void addProduct(int price, String type) {
        products.add(new Product(price, type));
    }


    public int total() {
        return products.stream().mapToInt(Product::getPrice).sum();
    }

    public double totalTaxReturn() {
        return products.stream().mapToDouble(Product::getTaxReturn).sum();
    }

    @Override
    public String toString() {
        return String.format("%d %d %.2f", id, total(), totalTaxReturn());
    }
}