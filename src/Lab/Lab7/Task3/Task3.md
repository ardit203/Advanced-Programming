# Lab Exercise 7 - Advanced Programming

## Task 3
## Task 1


<div class="clearfix" id="yui_3_18_1_1_1766186525434_85"><p data-start="182" data-end="353">You are given a Java program that performs multiple deposit and withdrawal operations on a shared bank account. The operations are executed concurrently as separate tasks.</p>
<p data-start="355" data-end="619">Your task is to ensure that access to the shared account balance is handled correctly when multiple operations are executed at the same time. The program must preserve the correctness of the balance regardless of the order or timing of execution of the operations.</p>
<p data-start="621" data-end="872" id="yui_3_18_1_1_1766186525434_84">Each deposit operation must always update the balance correctly. Each withdrawal operation must succeed only if sufficient funds are available at the moment the operation is applied. The balance must never become incorrect due to concurrent execution.</p>
<p data-start="874" data-end="1086">After all operations have completed, the program must output the final balance of the account. The printed balance must be deterministic and must reflect the correct result of all successfully applied operations.</p>
<p data-start="1088" data-end="1280">You must not change the input format, task creation logic, or execution framework. Your solution must work correctly under concurrent execution and must not rely on a specific execution order.</p>
<p data-start="1088" data-end="1280">Due to artificial delays in the tasks, testing the solution on CodeRunner will take some time.</p>
</div>




### Starter code
```java
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class BankBalance {

    // Shared bank account
    public static class BankAccount {
        private int balance;


        public BankAccount(int initialBalance) {
            this.balance = initialBalance;
        }

        public boolean deposit(int amount) {
            balance += amount;
            return true;
        }

        public boolean withdraw(int amount) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;

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
                    success = account.deposit(amount);
                } else { // withdraw
                    success = account.withdraw(amount);
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
```


### Solution
```java
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
```