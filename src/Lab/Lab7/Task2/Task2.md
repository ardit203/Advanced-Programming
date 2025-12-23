# Lab Exercise 7 - Advanced Programming

## Task 2
<div class="clearfix"><p data-start="327" data-end="661">You are given a starter Java program <code data-start="364" data-end="377">FakeApiPing</code> that simulates concurrent calls to a fake API. The API simulates work by waiting for a certain amount of time before returning a result, where the waiting time depends on the input parameter. Some API calls may take longer than others and may not finish within an allowed time limit.</p>
<p data-start="663" data-end="968">Your task is to complete the missing parts of the program so that all API calls are executed concurrently and their results are collected correctly. Each API call must be executed asynchronously and associated with a unique request identifier derived from the order in which the input parameters are read.</p>
<p data-start="970" data-end="1324">After scheduling all API calls for execution, the program must collect their results while respecting a maximum allowed waiting time for each call. If an API call finishes within the allowed time, its result should be recorded as successful. If an API call does not finish within the allowed time, it should be treated as failed and recorded accordingly.</p>
<p data-start="1326" data-end="1692">Since concurrent execution does not guarantee the order in which results become available, the program must ensure that all collected results are ordered by their request identifier before producing the final output. The final output must include one result for each API call, indicating whether it completed successfully or failed due to exceeding the allowed time.</p>
<p data-start="1694" data-end="1870">You must not change the input format, the provided API simulation, or the output format. The solution must correctly handle concurrent execution, timeouts, and result ordering.</p>
</div>



### Starter code
```java
package office_hours;

import java.util.*;
import java.util.concurrent.*;

public class FakeApiPing {

    // Result holder
    public static class ApiResult {
        public final int requestId;
        public final boolean success;
        public final String value;

        public ApiResult(int requestId, boolean success, String value) {
            this.requestId = requestId;
            this.success = success;
            this.value = value;
        }

        @Override
        public String toString() {
            return "ApiResult{" +
                    "requestId=" + requestId +
                    ", success=" + success +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class Api {
        public static ApiResult get(int requestId, int parameter) throws InterruptedException {
            long delayMillis = parameter * 100L;
            Thread.sleep(delayMillis);

            String response = "VALUE_" + parameter;
            return new ApiResult(requestId, true, response);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // number of API calls

        List<Callable<ApiResult>> tasks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int parameter = sc.nextInt();

            // requestId is the loop index
            int requestId = i+1;
            //TODO add a Callable that invokes the API get method in the tasks list
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<ApiResult>> futures = new ArrayList<>();
        //TODO submit all callables to the executure and get the Futures

        List<ApiResult> results = new ArrayList<>();

        long timeoutMillis = 200;

        //TODO get the ApiResult from all the futures and allow a max timeout of timeoutMillis

        executor.shutdown();

        // Sorting by requestId
        results.sort(Comparator.comparingInt(r -> r.requestId));

        // Output
        for (ApiResult r : results) {
            System.out.printf(
                    "%d %s %s%n",
                    r.requestId,
                    r.success ? "OK" : "FAILED",
                    r.value
            );
        }
    }
}
```


### Solution
```java
package Lab.Lab7.Task2;

import java.util.*;
import java.util.concurrent.*;

public class FakeApiPing {

    // Result holder
    public static class ApiResult {
        public final int requestId;
        public final boolean success;
        public final String value;

        public ApiResult(int requestId, boolean success, String value) {
            this.requestId = requestId;
            this.success = success;
            this.value = value;
        }

        @Override
        public String toString() {
            return "ApiResult{" +
                    "requestId=" + requestId +
                    ", success=" + success +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class Api {
        public static ApiResult get(int requestId, int parameter) throws InterruptedException {
            long delayMillis = parameter * 100L;
            Thread.sleep(delayMillis);

            String response = "VALUE_" + parameter;
            return new ApiResult(requestId, true, response);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // number of API calls

        List<Callable<ApiResult>> tasks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int parameter = sc.nextInt();

            // requestId is the loop index
            int requestId = i + 1;
            //TODO add a Callable that invokes the API get method in the tasks list
            tasks.add(() -> Api.get(requestId, parameter));
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<ApiResult>> futures = new ArrayList<>();
        //TODO submit all callables to the executure and get the Futures
        for (Callable<ApiResult> task : tasks) {
            futures.add(executor.submit(task));
        }


        List<ApiResult> results = new ArrayList<>();

        long timeoutMillis = 200;

        //TODO get the ApiResult from all the futures and allow a max timeout of timeoutMillis
        for (int i = 0; i < futures.size(); i++) {
            int requestId = i + 1;
            Future<ApiResult> future = futures.get(i);

            try {
                ApiResult apiResult = future.get(timeoutMillis, TimeUnit.MILLISECONDS);
                results.add(apiResult);
            } catch (ExecutionException | TimeoutException e) {
                future.cancel(true);
                results.add(new ApiResult(requestId, false, "TIMEOUT"));
            }
        }

        executor.shutdown();

        // Sorting by requestId
        results.sort(Comparator.comparingInt(r -> r.requestId));

        // Output
        for (ApiResult r : results) {
            System.out.printf(
                    "%d %s %s%n",
                    r.requestId,
                    r.success ? "OK" : "FAILED",
                    r.value
            );
        }
    }
}
```