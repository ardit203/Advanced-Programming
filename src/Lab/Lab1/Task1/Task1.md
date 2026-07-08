# Lab Exercise 1 - Advanced Programming

## Task 1

Create a banking application that will manage accounts for multiple users and perform transactions between them. The
bank operates in `US dollars`.

For this application, you need to write the classes `Transaction` and `Bank`. The class `Account` represents a single
user’s account and should store the following data:

- the user’s name
- a unique identification number (`long`)
- balance of the account (`real number`)

This class should also implement the following methods:

1. `Account(String name, double balance)` – constructor with parameters (the id should be generated using the
   java.util.Random class)
2. `getBalance(): double`
3. `getName(): String`
4. `getId(): long`
5. `setBalance(double balance)`
6. `toString(): String` – returns a string in the following format

   *Name: FirstName LastName*

   *Balance: 20.00$*

The class `Transaction` represents a transaction (transferring money from one account to another) performed by the bank,
for which a fee is often charged.  
To start, you should write the class `Transaction` with data members for the identification numbers of two accounts—one
from which the money is deducted and the other to which the money is added—along with a textual description and the
transaction amount.

This class should implement the following methods:

1. `Transaction(long fromId, long toId, String desc, double amount)`
2. `getAmount(): double`
3. `getFromId(): long`
4. `getToId(): long`

This class should be **immutable**, and you may also make it `abstract`, since it is not intended to be used directly but only as a base class for deriving other classes.

As mentioned previously, the bank charges a provision for certain transactions. There are two types of provision: a fixed amount and a percentage.
With a fixed amount, for any transaction regardless of the transaction amount, the same provision is charged (example: 10$).
With a percentage, a percentage of the entire transaction amount is calculated (the percentages are given as integers from 1–100).

To differentiate between the different types of fees, you should write two more classes that inherit from `Transaction`,
named:

- `FlatAmountProvisionTransaction`
- `FlatPercentProvisionTransaction`

This class `FlatAmountProvisionTransaction` should include:

- `FlatAmountProvisionTransaction(long fromId, long toId, double amount, double flatProvision)` – a constructor that
  sets the description field to `FlatAmount`
- `getFlatAmount(): double`

This class `FlatPercentProvisionTransaction` should include:

- `FlatPercentProvisionTransaction(long fromId, long toId, double amount, int centsPerDollar)` – a constructor that sets
  the description field to `FlatPercent`
- `getPercent(): int`

Also, the `equals(Object o): boolean` method should be overridden in both classes.

Finally, you should implement the class `Bank`, which stores the accounts of its users and additionally performs
transactions. Besides the users’ accounts, the class should store the bank’s own name, the total amount of transfers,
and the total amount of fees charged by the bank for all transactions.

The class `Bank` should have the following methods:

1. `Bank(String name, Account accounts[])` – constructor with appropriate parameters (make your own copy of the accounts
   array)
2. `makeTransaction(Transaction t): boolean` – checks whether the user has sufficient funds in the account and whether
   both accounts involved in the transaction actually belong to the bank. If both conditions are met, the transaction is
   performed and the method returns true; otherwise, it returns false.
3. `totalTransfers(): double` – returns the total amount of money transferred in all transactions so far
4. `totalProvision(): double` – returns the total fee charged by the bank for all transactions so far
5. `toString(): String` – returns the bank’s name on a separate line in the format

   *Name: Stopanska Banka A.D. Skopje*

   followed by the data for all users.

The fee is charged as follows: based on the transaction amount, the fee value (not the description) is added to the
transaction amount, and that sum is deducted from the first account.

Appropriate `equals` and `hashCode` methods should be written for all classes.

### Starter code:
```java
import java.util.*;
import java.util.stream.Collectors;

public class BankTester {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        String test_type = jin.nextLine();
        switch (test_type) {
            case "typical_usage":
                testTypicalUsage(jin);
                break;
            case "equals":
                testEquals();
                break;
        }
        jin.close();
    }

    private static double parseAmount (String amount){
        return Double.parseDouble(amount.replace("$",""));
    }

    private static void testEquals() {
        Account a1 = new Account("Andrej", 20.0);
        Account a2 = new Account("Andrej", 20.0);
        Account a3 = new Account("Andrej", 30.0);
        Account a4 = new Account("Gajduk", 20.0);
        List<Account> all = Arrays.asList(a1, a2, a3, a4);
        if (!(a1.equals(a1)&&!a1.equals(a2)&&!a2.equals(a1)&&!a3.equals(a1)
                && !a4.equals(a1)
                && !a1.equals(null))) {
            System.out.println("Your account equals method does not work properly.");
            return;
        }
        Set<Long> ids = all.stream().map(Account::getId).collect(Collectors.toSet());
        if (ids.size() != all.size()) {
            System.out.println("Different accounts have the same IDS. This is not allowed");
            return;
        }
        FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, 20.0, 10.0);
        FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(20, 20, 20.0, 10.0);
        FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, 20.0, 10.0);
        FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, 50.0, 50.0);
        FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, 20.0, 10.0);
        FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, 20.0, 10);
        FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, 20.0, 10);
        FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(10, 10, 20.0, 10);
        FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, 50.0, 10);
        FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, 20.0, 30);
        FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, 20.0, 10);
        if (fa1.equals(fa1) &&
                !fa2.equals(null) &&
                fa2.equals(fa1) &&
                fa1.equals(fa2) &&
                fa1.equals(fa3) &&
                !fa1.equals(fa4) &&
                !fa1.equals(fa5) &&
                !fa1.equals(fp1) &&
                fp1.equals(fp1) &&
                !fp2.equals(null) &&
                fp2.equals(fp1) &&
                fp1.equals(fp2) &&
                fp1.equals(fp3) &&
                !fp1.equals(fp4) &&
                !fp1.equals(fp5) &&
                !fp1.equals(fp6)) {
            System.out.println("Your transactions equals methods do not work properly.");
            return;
        }
        Account accounts[] = new Account[]{a1, a2, a3, a4};
        Account accounts1[] = new Account[]{a2, a1, a3, a4};
        Account accounts2[] = new Account[]{a1, a2, a3};
        Account accounts3[] = new Account[]{a1, a2, a3, a4};

        Bank b1 = new Bank("Test", accounts);
        Bank b2 = new Bank("Test", accounts1);
        Bank b3 = new Bank("Test", accounts2);
        Bank b4 = new Bank("Sample", accounts);
        Bank b5 = new Bank("Test", accounts3);

        if (!(b1.equals(b1) &&
                !b1.equals(null) &&
                !b1.equals(b2) &&
                !b2.equals(b1) &&
                !b1.equals(b3) &&
                !b3.equals(b1) &&
                !b1.equals(b4) &&
                b1.equals(b5))) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        accounts[2] = a1;
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        long from_id = a2.getId();
        long to_id = a3.getId();
        Transaction t = new FlatAmountProvisionTransaction(from_id, to_id, 3.0, 3.0);
        b1.makeTransaction(t);
        if (b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        b5.makeTransaction(t);
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        System.out.println("All your equals methods work properly.");
    }

    private static void testTypicalUsage(Scanner jin) {
        String bank_name = jin.nextLine();
        int num_accounts = jin.nextInt();
        jin.nextLine();
        Account accounts[] = new Account[num_accounts];
        for (int i = 0; i < num_accounts; ++i)
            accounts[i] = new Account(jin.nextLine(),  parseAmount(jin.nextLine()));
        Bank bank = new Bank(bank_name, accounts);
        while (true) {
            String line = jin.nextLine();
            switch (line) {
                case "stop":
                    return;
                case "transaction":
                    String descrption = jin.nextLine();
                    double amount = parseAmount(jin.nextLine());
                    double parameter = parseAmount(jin.nextLine());
                    int from_idx = jin.nextInt();
                    int to_idx = jin.nextInt();
                    jin.nextLine();
                    Transaction t = getTransaction(descrption, from_idx, to_idx, amount, parameter, bank);
                    System.out.println("Transaction amount: " + String.format("%.2f$",t.getAmount()));
                    System.out.println("Transaction description: " + t.getDescription());
                    System.out.println("Transaction successful? " + bank.makeTransaction(t));
                    break;
                case "print":
                    System.out.println(bank.toString());
                    System.out.println("Total provisions: " + String.format("%.2f$",bank.totalProvision()));
                    System.out.println("Total transfers: " + String.format("%.2f$",bank.totalTransfers()));
                    System.out.println();
                    break;
            }
        }
    }

    private static Transaction getTransaction(String description, int from_idx, int to_idx, double amount, double o, Bank bank) {
        switch (description) {
            case "FlatAmount":
                return new FlatAmountProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, o);
            case "FlatPercent":
                return new FlatPercentProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, (int) o);
        }
        return null;
    }


}
```

### Solution:
```java
import java.util.*;
import java.util.stream.Collectors;

class Account {
    private static long ID = 0;
    private String name;
    private long id;
    private double balance;

    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.id = ID++;
    }

    public static long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nBalance: %.2f$", name, balance);
    }
}

abstract class Transaction {
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

class FlatAmountProvisionTransaction extends Transaction {
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

class FlatPercentProvisionTransaction extends Transaction {
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


class Bank {
    private String name;
    private Account[] accounts;
    private double totalTransfers;
    private double totalProvisions;

    public Bank(String name, Account[] accounts) {
        this.name = name;
        this.accounts = Arrays.copyOf(accounts, accounts.length);
    }

    public boolean makeTransaction(Transaction t) {
        Account from = findAccount(t.getFromId());
        Account to = findAccount(t.getToId());

        double amount = t.getAmount();
        double provision = t.getProvision();

        if (!checkConditions(from, to, amount + provision)) {
            return false;
        }

        double fromBalance = from.getBalance();
        double toBalance = to.getBalance();

        if (from.getId() == to.getId()) {
            fromBalance = toBalance = fromBalance - provision;
        } else {
            fromBalance -= amount + provision;
            toBalance += amount;
        }
        from.setBalance(fromBalance);
        to.setBalance(toBalance);

        totalTransfers += amount;
        totalProvisions += provision;
        return true;
    }

    private Account findAccount(long id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    private boolean checkConditions(Account from, Account to, double amount) {
        return from != null && to != null && from.getBalance() >= amount;
    }

    public double totalProvision() {
        return totalProvisions;
    }

    public double totalTransfers() {
        return totalTransfers;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n\n");
        for (Account account : accounts) {
            sb.append(account).append("\n");
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(name, bank.name) &&
                Arrays.equals(accounts, bank.accounts) &&
                Objects.equals(totalTransfers, bank.totalTransfers) &&
                Objects.equals(totalProvisions, bank.totalProvisions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(accounts), totalTransfers, totalProvisions);
    }
}

public class BankTester {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        String test_type = jin.nextLine();
        switch (test_type) {
            case "typical_usage":
                testTypicalUsage(jin);
                break;
            case "equals":
                testEquals();
                break;
        }
        jin.close();
    }

    private static double parseAmount(String amount) {
        return Double.parseDouble(amount.replace("$", ""));
    }

    private static void testEquals() {
        Account a1 = new Account("Andrej", 20.0);
        Account a2 = new Account("Andrej", 20.0);
        Account a3 = new Account("Andrej", 30.0);
        Account a4 = new Account("Gajduk", 20.0);
        List<Account> all = Arrays.asList(a1, a2, a3, a4);
        if (!(a1.equals(a1) && !a1.equals(a2) && !a2.equals(a1) && !a3.equals(a1)
                && !a4.equals(a1)
                && !a1.equals(null))) {
            System.out.println("Your account equals method does not work properly.");
            return;
        }
        Set<Long> ids = all.stream().map(Account::getId).collect(Collectors.toSet());
        if (ids.size() != all.size()) {
            System.out.println("Different accounts have the same IDS. This is not allowed");
            return;
        }
        FlatAmountProvisionTransaction fa1 = new FlatAmountProvisionTransaction(10, 20, 20.0, 10.0);
        FlatAmountProvisionTransaction fa2 = new FlatAmountProvisionTransaction(20, 20, 20.0, 10.0);
        FlatAmountProvisionTransaction fa3 = new FlatAmountProvisionTransaction(20, 10, 20.0, 10.0);
        FlatAmountProvisionTransaction fa4 = new FlatAmountProvisionTransaction(10, 20, 50.0, 50.0);
        FlatAmountProvisionTransaction fa5 = new FlatAmountProvisionTransaction(30, 40, 20.0, 10.0);
        FlatPercentProvisionTransaction fp1 = new FlatPercentProvisionTransaction(10, 20, 20.0, 10);
        FlatPercentProvisionTransaction fp2 = new FlatPercentProvisionTransaction(10, 20, 20.0, 10);
        FlatPercentProvisionTransaction fp3 = new FlatPercentProvisionTransaction(10, 10, 20.0, 10);
        FlatPercentProvisionTransaction fp4 = new FlatPercentProvisionTransaction(10, 20, 50.0, 10);
        FlatPercentProvisionTransaction fp5 = new FlatPercentProvisionTransaction(10, 20, 20.0, 30);
        FlatPercentProvisionTransaction fp6 = new FlatPercentProvisionTransaction(30, 40, 20.0, 10);
        if (fa1.equals(fa1) &&
                !fa2.equals(null) &&
                fa2.equals(fa1) &&
                fa1.equals(fa2) &&
                fa1.equals(fa3) &&
                !fa1.equals(fa4) &&
                !fa1.equals(fa5) &&
                !fa1.equals(fp1) &&
                fp1.equals(fp1) &&
                !fp2.equals(null) &&
                fp2.equals(fp1) &&
                fp1.equals(fp2) &&
                fp1.equals(fp3) &&
                !fp1.equals(fp4) &&
                !fp1.equals(fp5) &&
                !fp1.equals(fp6)) {
            System.out.println("Your transactions equals methods do not work properly.");
            return;
        }
        Account accounts[] = new Account[]{a1, a2, a3, a4};
        Account accounts1[] = new Account[]{a2, a1, a3, a4};
        Account accounts2[] = new Account[]{a1, a2, a3};
        Account accounts3[] = new Account[]{a1, a2, a3, a4};

        Bank b1 = new Bank("Test", accounts);
        Bank b2 = new Bank("Test", accounts1);
        Bank b3 = new Bank("Test", accounts2);
        Bank b4 = new Bank("Sample", accounts);
        Bank b5 = new Bank("Test", accounts3);

        if (!(b1.equals(b1) &&
                !b1.equals(null) &&
                !b1.equals(b2) &&
                !b2.equals(b1) &&
                !b1.equals(b3) &&
                !b3.equals(b1) &&
                !b1.equals(b4) &&
                b1.equals(b5))) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        accounts[2] = a1;
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        long from_id = a2.getId();
        long to_id = a3.getId();
        Transaction t = new FlatAmountProvisionTransaction(from_id, to_id, 3.0, 3.0);
        b1.makeTransaction(t);
        if (b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        b5.makeTransaction(t);
        if (!b1.equals(b5)) {
            System.out.println("Your bank equals method do not work properly.");
            return;
        }
        System.out.println("All your equals methods work properly.");
    }

    private static void testTypicalUsage(Scanner jin) {
        String bank_name = jin.nextLine();
        int num_accounts = jin.nextInt();
        jin.nextLine();
        Account accounts[] = new Account[num_accounts];
        for (int i = 0; i < num_accounts; ++i)
            accounts[i] = new Account(jin.nextLine(), parseAmount(jin.nextLine()));
        Bank bank = new Bank(bank_name, accounts);
        while (true) {
            String line = jin.nextLine();
            switch (line) {
                case "stop":
                    return;
                case "transaction":
                    String descrption = jin.nextLine();
                    double amount = parseAmount(jin.nextLine());
                    double parameter = parseAmount(jin.nextLine());
                    int from_idx = jin.nextInt();
                    int to_idx = jin.nextInt();
                    jin.nextLine();
                    Transaction t = getTransaction(descrption, from_idx, to_idx, amount, parameter, bank);
                    System.out.println("Transaction amount: " + String.format("%.2f$", t.getAmount()));
                    System.out.println("Transaction description: " + t.getDescription());
                    System.out.println("Transaction successful? " + bank.makeTransaction(t));
                    break;
                case "print":
                    System.out.println(bank.toString());
                    System.out.println("Total provisions: " + String.format("%.2f$", bank.totalProvision()));
                    System.out.println("Total transfers: " + String.format("%.2f$", bank.totalTransfers()));
                    System.out.println();
                    break;
            }
        }
    }

    private static Transaction getTransaction(String description, int from_idx, int to_idx, double amount, double o, Bank bank) {
        switch (description) {
            case "FlatAmount":
                return new FlatAmountProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, o);
            case "FlatPercent":
                return new FlatPercentProvisionTransaction(bank.getAccounts()[from_idx].getId(),
                        bank.getAccounts()[to_idx].getId(), amount, (int) o);
        }
        return null;
    }


}
```
