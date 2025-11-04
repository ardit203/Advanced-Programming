package Book.Ch1_OOP_in_Java.Problem1_2;

public class FlatPercentProvisionTransaction extends Transaction {
    private int percent;

    public FlatPercentProvisionTransaction(long fromId, long toId, double amount, int percent) {
        super(fromId, toId, "FlatPercent", amount);
        this.percent = percent;
    }


    @Override
    public double getProvision() {
        return (long) (this.amount * percent / 100);

    }

    public int getPercent() {
        return percent;
    }
}
