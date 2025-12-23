package Exercises._08_Concurrent_Programming._2_ExecutorService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsTest2 {
    static void main() throws InterruptedException {
        int size = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(size);
        List<Runnable> runnables = TaskCreator.taskCreator(5);

        for (int i = 0; i < size; i++) {
            executorService.submit(runnables.get(i));
        }

        executorService.shutdown();
        if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
            executorService.shutdownNow();
            System.out.println("Stopping all tasks with force");
        }
    }
}
