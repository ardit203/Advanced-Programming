Implement the class `MojDDV` which reads information about scanned fiscal receipts (bills) from one user of the same-named application.
The data for each fiscal receipt has the following format:

```
ID item_price1 item_tax_type1 item_price2 item_tax_type2 … item_price-n item_tax_type-n
```

**Example:**
```
12334 1789 A 1238 B 1222 V 111 V
```


There are three VAT (Value Added Tax) types:

* **A** (18% tax rate)
* **B** (5% tax rate)
* **V** (0% tax rate)

The VAT refund is 15% of the VAT amount for each item.


Implement the following methods:

- `void readRecords(InputStream inputStream)` - 
A method that reads fiscal receipts from the input stream.
If the scanned receipt has a total value **greater than 30,000 denars**, the method must throw an exception of type `AmountNotAllowedException`.
You must decide where the exception is thrown, and where it is caught so that the function continues reading all remaining receipts.
The exception should print the message: `Receipt with amount [SUM OF ALL ITEMS] is not allowed to be scanned`

- `void printTaxReturns(OutputStream outputStream)` - 
This method prints all scanned fiscal receipts in the format:
`ID SUM_OF_AMOUNTS TAX_RETURN` Where:
   - `SUM_OF_AMOUNTS` = sum of all item prices in the receipt
   - `TAX_RETURN` = calculated VAT refund for that receipt


### Additional:

 - `void printStatistics (OutputStream outputStream)` – a method that prints statistics about the VAT refund from all scanned fiscal receipts to the output stream in the format
`min: MIN max: MAX sum: SUM count: COUNT average: AVERAGE`. Each statistic is printed on a new line, and the value of the statistic is separated from the name of the statistic by a **tab** character (see the test example).
Decimal values are printed with **5 total places**, of which **3 are after the decimal point**.
Integer values are written with **5 places**, **left-aligned**.

- Printing the values in the method `printTaxReturns` is done in the following way:
- All pieces of information are separated by a **tab**.
- The **id** and the **amount** of the fiscal receipt are printed with **10 places**.
- The **VAT refund** is printed with **10 places**, of which **5 are after the decimal point**.

### Starter code
````java
public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        mojDDV.printStatistics(System.out);

    }
}
````

### Solution
```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;


public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
        mojDDV.printStatistics(System.out);

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
        return String.format("%10d\t%10d\t%10.5f",id,totalAmount(), totalTaxReturn());
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
    
    //    private DoubleStream getDoubleStream(){
//        return receipts.stream().mapToDouble(Receipt::totalTaxReturn);
//    }
//
//    public void printStatistics(PrintStream os) {
//        PrintWriter pw = new PrintWriter(os);
//
//        double min = getDoubleStream().min().orElse(0);
//        double max = getDoubleStream().max().orElse(0);
//        double sum = getDoubleStream().sum();
//        int count = receipts.size();
//        double avg = getDoubleStream().average().orElse(0);
//
//        pw.println(String.format("min:\t%5.3f", min));
//        pw.println(String.format("max:\t%5.3f", max));
//        pw.println(String.format("sum:\t%5.3f", sum));
//        pw.println(String.format("count:\t%-5d", count));
//        pw.println(String.format("avg:\t%5.3f", avg));
//
//        pw.flush();
//    }

    public void printStatistics(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);

        DoubleSummaryStatistics summaryStatistics = receipts.stream()
                .mapToDouble(Receipt::totalTaxReturn)
                .summaryStatistics();

        pw.println(String.format("min:\t%5.3f", summaryStatistics.getMin()));
        pw.println(String.format("max:\t%5.3f", summaryStatistics.getMax()));
        pw.println(String.format("sum:\t%5.3f", summaryStatistics.getSum()));
        pw.println(String.format("count:\t%-5d", summaryStatistics.getCount()));
        pw.println(String.format("avg:\t%5.3f", summaryStatistics.getAverage()));

        pw.flush();
    }
}
```