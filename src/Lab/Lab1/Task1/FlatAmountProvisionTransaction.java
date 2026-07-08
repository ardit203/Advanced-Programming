package Lab.Lab1.Task1;

public class FlatAmountProvisionTransaction extends Transaction {
    private double flatAmount;

    public FlatAmountProvisionTransaction(long fromId, long toId, double amount, double flatAmount) {
        super(fromId, toId, "FlatAmount", amount);
        this.flatAmount = flatAmount;
    }

    public double getFlatAmount() {
        return flatAmount;
    }

    @Override
    public double getProvision() {
        return flatAmount;
    }
}