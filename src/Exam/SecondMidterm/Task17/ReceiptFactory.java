package Exam.SecondMidterm.Task17;

public class ReceiptFactory {
    public static Receipt create(String line) {
        String[] tokens = line.split("\\s++");

        int id = Integer.parseInt(tokens[0]);
        Receipt receipt = new Receipt(id);
        for (int i = 1; i < tokens.length - 1; i+=2) {
            int price = Integer.parseInt(tokens[i]);
            String taxType = tokens[i + 1];
            double taxReturn = taxReturn(price, taxType);
            receipt.addItem(price, taxReturn);
        }
        int sum = receipt.sum();
        if (sum > 30000) {
            throw new AmountNotAllowedException(sum);
        }
        return receipt;
    }

    private static double taxReturn(int price, String type) {
        if (type.equals("A")) {
            return price * 0.18 * 0.15;
        } else if (type.equals("B")) {
            return price * 0.05 * 0.15;
        }
        return 0;
    }
}
