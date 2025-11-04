package Book.Ch1_OOP_in_Java.Problem1_2;

public class FlatAmountProvisionTransaction extends Transaction {
    private double flatAmount;

    public FlatAmountProvisionTransaction(long fromId, long toId, double amount, double flatAmount) {
        super(fromId, toId, "FlatAmount", amount);
        this.flatAmount = flatAmount;
    }

    @Override
    public double getProvision() {
        return flatAmount;
    }

    public double getFlatAmount() {
        return flatAmount;
    }
}
