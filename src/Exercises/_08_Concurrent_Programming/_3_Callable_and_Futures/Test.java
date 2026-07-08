package Exercises._08_Concurrent_Programming._3_Callable_and_Futures;

import java.util.List;
import java.util.concurrent.*;

public class Test {
    static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Callable<Integer>> callables = CallableCreator.createCallables(5);

        List<Future<Integer>> futures = executorService.invokeAll(callables, 3, TimeUnit.SECONDS);



        int sum = 0;
        for (Future<Integer> future : futures) {
            try{
                sum += future.get(500, TimeUnit.MILLISECONDS);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

        System.out.println(sum);
        executorService.shutdownNow();
    }
}
