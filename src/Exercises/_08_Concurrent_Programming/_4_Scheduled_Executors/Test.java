package Exercises._08_Concurrent_Programming._4_Scheduled_Executors;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {
    static void main(String[] args) {
        Runnable taskDelay = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Delay task started");
        };
        Runnable taskRate = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Rate task started");
        };


        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(taskRate, 3, 3, TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(taskDelay, 3, 3, TimeUnit.SECONDS);
    }
}
