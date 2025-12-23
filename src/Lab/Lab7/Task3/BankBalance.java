package Lab.Lab7.Task3;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class BankBalance {

    // Shared bank account
    public static class BankAccount {
        private int balance;
        public final ReentrantLock lock = new ReentrantLock();


        public BankAccount(int initialBalance) {
            this.balance = initialBalance;
        }

        public boolean deposit(int amount, long timeOut) throws InterruptedException {
            if (amount <= 0) return false;

            boolean locked = lock.tryLock(timeOut, TimeUnit.MILLISECONDS);
            if (!locked) return false;

            try {
                balance += amount;
                return true;
            } finally {
                lock.unlock();
            }
        }

        public boolean withdraw(int amount, long timeOut) {
            if (amount <= 0) return false;

            boolean locked;
            try {
                locked = lock.tryLock(timeOut, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }

            if (!locked) return false;

            try {
                if (balance < amount) return false;
                balance -= amount;
                return true;
            } finally {
                lock.unlock();
            }
        }


        public int getBalance() {
            return balance;
        }
    }


    // Operation result
    public static class OperationResult {
        public final int operationId;
        public final boolean success;

        public OperationResult(int operationId, boolean success) {
            this.operationId = operationId;
            this.success = success;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int initialBalance = sc.nextInt();
        int n = sc.nextInt(); // number of operations

        BankAccount account = new BankAccount(initialBalance);

        List<Callable<OperationResult>> tasks = new ArrayList<>();

        long lockTimeoutMs = 100; // max time to wait for the lock

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            int amount = sc.nextInt();
            int operationId = i + 1;

            tasks.add(() -> {
                Thread.sleep(3000);
                boolean success;
                if (type.equals("deposit")) {
                    success = account.deposit(amount, lockTimeoutMs);
                } else { // withdraw
                    success = account.withdraw(amount, lockTimeoutMs);
                }
                return new OperationResult(operationId, success);
            });
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(4);

        List<Future<OperationResult>> futures = executor.invokeAll(tasks);

        List<OperationResult> results = new ArrayList<>();
        for (Future<OperationResult> f : futures) {
            results.add(f.get());
        }

        executor.shutdown();

        // Deterministic final balance
        System.out.println("FINAL_BALANCE " + account.getBalance());
    }

}
