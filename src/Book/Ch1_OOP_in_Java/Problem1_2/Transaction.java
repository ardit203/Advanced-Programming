package Book.Ch1_OOP_in_Java.Problem1_2;

import java.util.Objects;

public abstract class Transaction {
    protected long fromId;
    protected long toId;
    protected String description;
    protected double amount;

    public Transaction(long fromId, long toId, String description, double amount) {
        this.fromId = fromId;
        this.toId = toId;
        this.description = description;
        this.amount = amount;
    }

    public long getFromId() {
        return fromId;
    }

    public long getToId() {
        return toId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public abstract double getProvision();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Transaction t = (Transaction) obj;
        return Objects.equals(this.description, t.description)
                && Objects.equals(this.fromId, t.fromId)
                && Objects.equals(this.toId, t.toId)
                && Objects.equals(this.amount, t.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromId, toId, description, amount);
    }
}
