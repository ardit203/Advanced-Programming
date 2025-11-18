package Exam.FirstMidterm.Task16;


public class ReceiptFactory {
    public static Receipt create(String line) {
        String[] tokens = line.split(" ");
        int id = Integer.parseInt(tokens[0]);
        Receipt receipt = new Receipt(id);

        for (int i = 1; i < tokens.length - 1; i += 2) {
            int price = Integer.parseInt(tokens[i]);
            String vatType = tokens[i + 1];
            receipt.addProduct(price, vatType);
        }

        int total = receipt.total();
        if (total > 30000) {
            throw new AmountNotAllowedException(total);
        }
        return receipt;
    }
}