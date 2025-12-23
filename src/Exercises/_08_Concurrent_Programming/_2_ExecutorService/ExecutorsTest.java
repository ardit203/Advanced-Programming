package Exercises._08_Concurrent_Programming._2_ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {
    static void main() {
        //This newSingleThreadExecutor only executes on Thread at a time
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable task = TaskCreator.taskCreator(1).get(0);

//        executorService.submit(task);

        for (int i = 0; i < 5; i++) {
            executorService.submit(task);
        }

        //Without this the service will endlessly listen for new tasks
        executorService.shutdown();
    }
}
