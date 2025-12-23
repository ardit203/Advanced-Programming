package Exercises._08_Concurrent_Programming._3_Callable_and_Futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallableCreator {
    public static List<Callable<Integer>> createCallables(int n){
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            callables.add(
                    () -> {
                        TimeUnit.SECONDS.sleep(3);
                        return 2;
                    }
            );
        }
        return callables;
    }
}
