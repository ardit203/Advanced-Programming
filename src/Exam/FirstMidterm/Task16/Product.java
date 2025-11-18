package Exam.FirstMidterm.Task16;

public class Product {
    private int price;
    private String type;

    public Product(int price, String type) {
        this.price = price;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public double getTaxReturn() {
        if (type.equals("A")) {
            return price * 0.18 * 0.15;
        } else if (type.equals("B")) {
            return price * 0.05 * 0.15;
        }
        return 0;
    }
}