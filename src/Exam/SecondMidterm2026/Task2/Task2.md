<div class="clearfix" id="yui_3_18_1_1_1771366750080_85">
<p data-start="0" data-end="286">It is required to implement a concurrent service for storing integers and obtaining (when needed) statistics for the already stored numbers. At the same time, the service can be accessed by multiple threads, both for writing a new number and for reading one of the available statistics.</p>
<ol data-start="288" data-end="1874" data-is-last-node="" data-is-only-node="">
<li data-start="288" data-end="650">
<p data-start="291" data-end="650">Implement a class <strong data-start="309" data-end="330">StatisticsService</strong> that manages integer values and provides methods for adding a new integer to the collection, as well as methods for obtaining the count, minimum, maximum, and average value of the numbers in the collection.<br data-start="537" data-end="540">Only one object of this class is created in the <code data-start="591" data-end="597">main</code> function, and that same object is used by all tasks.</p>
</li>
<li data-start="652" data-end="1533">
<p data-start="655" data-end="765">Define classes (for tasks that will be executed concurrently) that implement the <code data-start="736" data-end="754">Callable&lt;String&gt;</code> interface:</p>
<ul data-start="769" data-end="1533">
<li data-start="769" data-end="976">
<p data-start="771" data-end="976"><strong data-start="771" data-end="791">SubmitNumberTask</strong> – a task that adds a new number to the service (<strong data-start="840" data-end="861" data-is-only-node="">StatisticsService</strong>) and returns a string describing the performed operation as a result<br data-start="930" data-end="933"><em data-start="938" data-end="976">(NUMBER %d ADDED. Total numbers: %d)</em></p>
</li>
<li data-start="980" data-end="1167">
<p data-start="982" data-end="1167"><strong data-start="982" data-end="1000">GetAverageTask</strong> – a task that retrieves the average of the added numbers from the service and returns a string describing the performed operation as a result<br data-start="1142" data-end="1145" data-is-only-node=""><em data-start="1150" data-end="1167">(AVERAGE: %.2f)</em></p>
</li>
<li data-start="1171" data-end="1350">
<p data-start="1173" data-end="1350"><strong data-start="1173" data-end="1187">GetMinTask</strong> – a task that retrieves the minimum of the added numbers from the service and returns a string describing the performed operation as a result<br data-start="1329" data-end="1332" data-is-only-node=""><em data-start="1337" data-end="1350">(MIN: %.2f)</em></p>
</li>
<li data-start="1354" data-end="1533">
<p data-start="1356" data-end="1533"><strong data-start="1356" data-end="1370">GetMaxTask</strong> – a task that retrieves the maximum of the added numbers from the service and returns a string describing the performed operation as a result<br data-start="1512" data-end="1515" data-is-only-node=""><em data-start="1520" data-end="1533">(MAX: %.2f)</em></p>
</li>
</ul>
</li>
<li data-start="1535" data-end="1874" data-is-last-node="">
<p data-start="1538" data-end="1874" data-is-last-node="">Implement a class <strong data-start="1556" data-end="1577">ConcurrentService</strong> with a single static method <code data-start="1606" data-end="1617">submitAll</code> that takes two arguments: the number of threads that can be used and a list of <code data-start="1697" data-end="1715">Callable&lt;String&gt;</code> objects. The method should execute all callable tasks using an <code data-start="1779" data-end="1796">ExecutorService</code> with the specified number of threads and return a list of all future results.</p>
</li>
</ol></div>

### Starter code
```java
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;


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

        for (int i = 1; i < k*100; i++) {
            int value = i * 10;
            tasks.add(new SubmitNumberTask(service, value));
            expectedMax = Math.max(expectedMax, value);
            added++;
        }

        /* ------------------------------------------------------------
           PHASE 2: Concurrent readers (should run in parallel)
           ------------------------------------------------------------ */

        for (int i = 0; i < k*5; i++) {
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

        for (int i = 100; i <= k*200; i += 10) {
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
            try{
                finalResults.add(f.get());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        int numberAddedMessage = 0, minInvoked = 0, maxInvoked = 0, averageInvoked = 0;

        for (String finalResult : finalResults) {
            if (finalResult.startsWith("AVERAGE")) {
                averageInvoked++;
            }
            if  (finalResult.startsWith("MIN")) {
                minInvoked++;
            }
            if (finalResult.startsWith("MAX")) {
                maxInvoked++;
            }
            if (finalResult.contains("Total numbers: ")) {
                numberAddedMessage++;
            }
        }

        if (minInvoked!=min){
            System.out.println("GetMinTask was not invoked the correct number of times");
        }

        if (maxInvoked!=max){
            System.out.println("GetMaxTask was not invoked the correct number of times");
        }

        if (averageInvoked!=avg){
            System.out.println("GetAverageTask was not invoked the correct number of times");
        }

        if (numberAddedMessage!=added) {
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
```