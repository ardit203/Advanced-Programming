package Exercises._08_Concurrent_Programming._2_ExecutorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskCreator {

    public static List<Runnable> taskCreator(int n){
        List<Runnable> runnables = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            runnables.add(
                    () ->{
                        try {
                            Thread.sleep(3000);
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Hello " + Thread.currentThread().getName());
                    }
            );
        }
        return runnables;
    }
}
