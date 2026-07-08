You need to implement a class **MojDDV** which will read information from an input stream about scanned fiscal receipts made by a user of the application.
The data for the fiscal receipts is in the following format:

`ID item_price1 item_tax_type1 item_price2 item_tax_type2 … item_price-n item_tax_type-n`

For example: `12334 1789 A 1238 B 1222 V 111 V`

There are three types of VAT:

* `A` (18% of the value)
* `B` (5% of the value)
* `V` (0% of the value)

The VAT return is 15% of the value added tax for the item.

You need to implement the following methods:

* `void readRecords (InputStream inputStream)` – a method which reads the data for fiscal receipts from the input stream. If a scanned fiscal receipt has a total amount greater than 30000 denars, an exception of type `AmountNotAllowedException` must be thrown. Define where the exception will be thrown and where it will be caught, in such a way that this function can still read all scanned fiscal receipts. The exception should print the message `“Receipt with amount [sum of all items] is not allowed to be scanned”`.

* `void printTaxReturns (OutputStream outputStream)` – a method which prints all scanned fiscal receipts to the output stream in the format `“ID SUM_OF_AMOUNTS TAX_RETURN”`, where `SUM_OF_AMOUNTS` is the sum of all items in the fiscal receipt, and `TAX_RETURN` is the calculated VAT return for that fiscal receipt.

### Starter code
```java
public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

    }
}
```

### Solution
```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

    }
}


class AmountNotAllowedException extends RuntimeException{
    public AmountNotAllowedException(int sum) {
        super(String.format("Receipt with amount %d is not allowed to be scanned", sum));
    }
}


class Item {
    private int price;
    private double taxReturn;

    public Item(int price, double taxReturn) {
        this.price = price;
        this.taxReturn = taxReturn;
    }

    public int getPrice() {
        return price;
    }

    public double getTaxReturn() {
        return taxReturn;
    }
}

class Receipt {
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

class MojDDV {
    private List<Receipt> receipts;

    public MojDDV() {
        this.receipts = new ArrayList<>();
    }

    public void readRecords(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        receipts = br.lines()
                .map(line -> {
                    try {
                        return Receipt.createReceipt(line);
                    } catch (AmountNotAllowedException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    void printTaxReturns(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        receipts.forEach(pw::println);
        pw.flush();
    }
}
```