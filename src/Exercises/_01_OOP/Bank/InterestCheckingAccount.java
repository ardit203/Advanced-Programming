package Exercises._01_OOP.Bank;

public class InterestCheckingAccount extends Account implements InterestBearingAccount {
    protected static double INTEREST_RATE = 0.03;

    public InterestCheckingAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public void addInterest() {
        deposit(getBalance() * INTEREST_RATE);
    }
}
