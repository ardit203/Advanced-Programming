package Exam.FirstMidterm.Task25;

import java.util.Comparator;

public class Product implements Comparable<Product> {
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

