package Exam.FirstMidterm.Task25;

public class Item {
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
