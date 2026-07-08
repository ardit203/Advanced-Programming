package Exam.SecondMidterm2026.Task2;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;


class StatisticsService {
    private int count = 0;
    private int sum = 0;
    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public String submitNumber(int number) {
        lock.writeLock().lock();
        try {
            count++;
            sum += number;
            min = Math.min(min, number);
            max = Math.max(max, number);

            return "Added number: " + number + ". Total numbers: " + count;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getAverage() {
        lock.readLock().lock();
        try {
            if (count == 0) return 0;
            return sum / count;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getMin() {
        lock.readLock().lock();
        try {
            if (count == 0) return 0;
            return min;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getMax() {
        lock.readLock().lock();
        try {
            if (count == 0) return 0;
            return max;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getCount() {
        lock.readLock().lock();
        try {
            return count;
        } finally {
            lock.readLock().unlock();
        }
    }
}

class SubmitNumberTask implements Callable<String> {
    private final StatisticsService service;
    private final int number;

    public SubmitNumberTask(StatisticsService service, int number) {
        this.service = service;
        this.number = number;
    }

    @Override
    public String call() {
        return service.submitNumber(number);
    }
}

class GetAverageTask implements Callable<String> {
    private final StatisticsService service;

    public GetAverageTask(StatisticsService service) {
        this.service = service;
    }

    @Override
    public String call() {
        return "AVERAGE: " + service.getAverage();
    }
}

class GetMinTask implements Callable<String> {
    private final StatisticsService service;

    public GetMinTask(StatisticsService service) {
        this.service = service;
    }

    @Override
    public String call() {
        return "MIN: " + service.getMin();
    }
}

class GetMaxTask implements Callable<String> {
    private final StatisticsService service;

    public GetMaxTask(StatisticsService service) {
        this.service = service;
    }

    @Override
    public String call() {
        return "MAX: " + service.getMax();
    }
}

class ConcurrentService {
    public static List<Future<String>> submitAll(int numberOfThreads, List<Callable<String>> tasks)
            throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        try {
            return executor.invokeAll(tasks);
        } finally {
            executor.shutdown();
        }
    }
}

public class StatisticsExam {

    public static void main(String[] args) throws Exception {

        StatisticsService service = new StatisticsService();

        int k;
        Scanner scanner = new Scanner(System.in);
        k = scanner.nextInt();


        List<Callable<String>> tasks = new ArrayList<>();

        /* ------------------------------------------------------------
           PHASE 1: Concurrent writers
           ------------------------------------------------------------ */

        int added = 0;
        int avg = 0;
        int min = 0;
        int max = 0;

        int expectedMin = 10;
        int expectedMax = 10;

        for (int i = 1; i < k * 100; i++) {
            int value = i * 10;
            tasks.add(new SubmitNumberTask(service, value));
            expectedMax = Math.max(expectedMax, value);
            added++;
        }

        /* ------------------------------------------------------------
           PHASE 2: Concurrent readers (should run in parallel)
           ------------------------------------------------------------ */

        for (int i = 0; i < k * 5; i++) {
            tasks.add(new GetAverageTask(service));
            avg++;
            tasks.add(new GetMinTask(service));
            min++;
            tasks.add(new GetMaxTask(service));
            max++;
        }

        /* ------------------------------------------------------------
           PHASE 3: Interleaved read/write (critical part)
           ------------------------------------------------------------ */

        for (int i = 100; i <= k * 200; i += 10) {
            tasks.add(new SubmitNumberTask(service, i));
            added++;
            expectedMax = Math.max(expectedMax, i);
            tasks.add(new GetAverageTask(service));
            avg++;
            tasks.add(new GetMinTask(service));
            min++;
            tasks.add(new GetMaxTask(service));
            max++;
        }

        /* ------------------------------------------------------------
           EXECUTION
           ------------------------------------------------------------ */


        List<Future<String>> results = ConcurrentService.submitAll(6, tasks);


        List<String> finalResults = new ArrayList<>();
        for (Future<String> f : results) {
            try {
                finalResults.add(f.get());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        int numberAddedMessage = 0, minInvoked = 0, maxInvoked = 0, averageInvoked = 0;

        for (String finalResult : finalResults) {
            if (finalResult.startsWith("AVERAGE")) {
                averageInvoked++;
            }
            if (finalResult.startsWith("MIN")) {
                minInvoked++;
            }
            if (finalResult.startsWith("MAX")) {
                maxInvoked++;
            }
            if (finalResult.contains("Total numbers: ")) {
                numberAddedMessage++;
            }
        }

        if (minInvoked != min) {
            System.out.println("GetMinTask was not invoked the correct number of times");
        }

        if (maxInvoked != max) {
            System.out.println("GetMaxTask was not invoked the correct number of times");
        }

        if (averageInvoked != avg) {
            System.out.println("GetAverageTask was not invoked the correct number of times");
        }

        if (numberAddedMessage != added) {
            System.out.println("Number of added tasks was not invoked the correct number of times");
        }

        /* ------------------------------------------------------------
           BASIC SANITY CHECKS (NO assert, exam-safe)
           ------------------------------------------------------------ */

        int finalCount = service.getCount();


        if (finalCount != added) {
            throw new RuntimeException(
                    String.format("ERROR: Expected %d numbers, but got %d", added, finalCount)
            );
        }

        if (service.getMin() != expectedMin) {
            throw new RuntimeException(
                    "ERROR: Expected MIN = " + expectedMin
            );
        }

        if (service.getMax() != expectedMax) {
            throw new RuntimeException(
                    "ERROR: Expected MAX = " + expectedMax
            );
        }

        System.out.println("✔ FINAL CHECKS PASSED");
    }
}