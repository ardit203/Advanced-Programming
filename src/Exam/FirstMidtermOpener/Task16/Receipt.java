package Exam.FirstMidtermOpener.Task16;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private long id;
    private List<Item> items;

    public Receipt(long id, List<Item> items) {
        this.id = id;
        this.items = items;
    }

    public int totalAmount() {
        return items.stream()
                .mapToInt(Item::getPrice)
                .sum();
    }

    public double totalTaxReturn() {
        return items.stream()
                .mapToDouble(Item::getTaxReturn)
                .sum();
    }

    @Override
    public String toString() {
        return String.format("%d %d %.2f", id, totalAmount(), totalTaxReturn());
    }

    public static Receipt createReceipt(String line) {
        String[] tokens = line.split("\\s++");
        long id = Long.parseLong(tokens[0]);
        int sum = 0;
        List<Item> itemList = new ArrayList<>();
        for (int i = 1; i < tokens.length - 1; i += 2) {
            int price = Integer.parseInt(tokens[i]);
            String vatType = tokens[i + 1];
            itemList.add(new Item(price, getTaxReturn(price, vatType)));
            sum += price;
        }
        if (sum > 30000) {
            throw new AmountNotAllowedException(sum);
        }
        return new Receipt(id, itemList);
    }

    private static double getTaxReturn(int price, String vatType) {
        switch (vatType) {
            case "A":
                return price * 0.18 * 0.15;
            case "B":
                return price * 0.05 * 0.15;
            case "V":
                return 0;
            default:
                throw new RuntimeException("Wrong vat type");
        }
    }
}
