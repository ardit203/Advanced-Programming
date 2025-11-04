package Book.Ch1_OOP_in_Java.Problem1_2;

import java.util.Arrays;
import java.util.Objects;

public class Bank {
    private String name;
    private Account[] accounts;
    private double totalTransfers;
    private double totalProvision;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts = accounts;
    }

    public boolean makeTransaction(Transaction t) {
        Account from = find(t.getFromId());
        Account to = find(t.getToId());

        if (from != null && to != null) {
            double fromBalance = from.getBalance();
            double toBalance = to.getBalance();
            double provision = t.getProvision();
            double amount = t.getAmount();
            if (fromBalance >= provision + amount) {
                if (from.getId() == to.getId()) {
                    fromBalance -= amount + provision;
                    toBalance = fromBalance;
                } else {
                    fromBalance -= amount + provision;
                    toBalance += amount;
                }
                from.setBalance(fromBalance);
                to.setBalance(toBalance);
                this.totalProvision += provision;
                this.totalTransfers += amount;
                return true;
            }
        }
        return false;
    }

    private Account find(long id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }


    public Account[] getAccounts() {
        return accounts;
    }

    public double totalProvision() {
        return totalProvision;
    }

    public double totalTransfers() {
        return totalTransfers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n\n");
        for (Account account : accounts) {
            sb.append(account);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(totalTransfers, bank.totalTransfers) == 0
                && Double.compare(totalProvision, bank.totalProvision) == 0
                && Objects.equals(name, bank.name)
                && Objects.deepEquals(accounts, bank.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(accounts), totalTransfers, totalProvision);
    }
}
