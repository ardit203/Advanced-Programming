package Lab.Lab1.Task1;

public class FlatPercentProvisionTransaction extends Transaction {
    private int percent;

    public FlatPercentProvisionTransaction(long fromId, long toId, double amount, int percent) {
        super(fromId, toId, "FlatPercent", amount);
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }


    @Override
    public double getProvision() {
        return amount * percent / 100;
    }
}