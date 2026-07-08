package Lab.Lab1.Task1;

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

    public abstract double getProvision();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromId, toId, description, amount);
    }

    public String getDescription() {
        return description;
    }
}