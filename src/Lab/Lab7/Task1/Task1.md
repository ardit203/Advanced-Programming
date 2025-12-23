# Lab Exercise 7 - Advanced Programming

<div class="clearfix" id="yui_3_18_1_1_1766273129294_84"><p data-start="0" data-end="334">You are given a starter Java program <code data-start="37" data-end="55">TextCounter.java</code> that reads multiple texts from standard input and processes them using concurrent programming. Parts of the code are intentionally left incomplete. Your task is to complete these parts by applying concepts such as <code data-start="270" data-end="280">Callable</code>, lambda expressions, <code data-start="302" data-end="319">ExecutorService</code>, and <code data-start="325" data-end="333">Future</code>.</p>
<p data-start="336" data-end="1009" id="yui_3_18_1_1_1766273129294_83">First, you need to implement the method <code data-start="376" data-end="417">getTextCounter(int textId, String text)</code>. This method must return a <code data-start="445" data-end="464">Callable&lt;Counter&gt;</code>. You are not allowed to create a separate class that implements <code data-start="529" data-end="539">Callable</code>; instead, you must use a lambda expression. The computation of the text statistics must happen inside the <code data-start="646" data-end="656">Callable</code>, not before it is created. When the callable is executed, it should count how many lines the text contains, how many words it contains (where words are separated by one or more whitespace characters), and how many characters the text contains. The callable must return a new <code data-start="932" data-end="941">Counter</code> object initialized with the given <code data-start="976" data-end="984">textId</code> and the computed values.</p>
<p data-start="1011" data-end="1336">Next, inside the <code data-start="1028" data-end="1034">main</code> method, after each text is read from standard input, you need to create a corresponding <code data-start="1123" data-end="1142">Callable&lt;Counter&gt;</code> by calling <code data-start="1154" data-end="1170">getTextCounter</code> with the appropriate arguments. Each callable must then be added to the provided list of tasks. There must be exactly one callable for each text read from the input.</p>
<p data-start="1338" data-end="1715">After all callables have been added to the task list, you need to execute them concurrently using the provided <code data-start="1449" data-end="1466">ExecutorService</code>. You must invoke all tasks on the executor and obtain a list of <code data-start="1531" data-end="1539">Future</code> objects that represent the pending results of the concurrent computations. You may use any appropriate method of <code data-start="1653" data-end="1670">ExecutorService</code> that executes callables and returns futures.</p>
<p data-start="1717" data-end="1979">Once the tasks have been executed, you must extract the results from the futures. For each future, retrieve the corresponding <code data-start="1843" data-end="1852">Counter</code> object and add it to the results list. This step should correctly handle the fact that task execution order is not guaranteed.</p>
<p data-start="1981" data-end="2313" data-is-last-node="" data-is-only-node="">You must not modify any other parts of the code. In particular, you must not change the input format, the structure of the <code data-start="2104" data-end="2113">Counter</code> class, or the sorting and printing logic at the end of the program. The final output of the program must be the statistics for all texts, sorted by <code data-start="2262" data-end="2270">textId</code>, exactly as produced by the provided code.</p>
</div>

### Starter Code
```java
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class TextCounter {

    // Result holder
    public static class Counter {
        public final int textId;
        public final int lines;
        public final int words;
        public final int chars;

        public Counter(int textId, int lines, int words, int chars) {
            this.textId = textId;
            this.lines = lines;
            this.words = words;
            this.chars = chars;
        }

        @Override
        public String toString() {
            return "Counter{" +
                    "textId=" + textId +
                    ", lines=" + lines +
                    ", words=" + words +
                    ", chars=" + chars +
                    '}';
        }


    }

    
    public static Callable<Counter> getTextCounter(int textId, String text) {
        //TODO
    }

    

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();       // number of texts
        sc.nextLine();              // consume newline

        List<Callable<Counter>> tasks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int textId = sc.nextInt();
            sc.nextLine();          // consume newline

            int lines = sc.nextInt();   // number of lines for this text
            sc.nextLine();              // consume newline

            StringBuilder text = new StringBuilder();
            for (int j = 0; j < lines; j++) {
                text.append(sc.nextLine());
                if (j < lines - 1) {
                    text.append("\n");
                }
            }

            //TODO add a Callable<Counter> for each text read in the tasks list
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                

        //TODO invoke All tasks on the executor and create a List<Future<?>>
        

        List<Counter> results = new ArrayList<>();
        
        //TODO extract results from the List<Future>

        executor.shutdown();


        // Sorting by textId (important concept!)
        results.sort(Comparator.comparingInt(c -> c.textId));

        // Output (optional for debugging / demonstration)
        for (Counter c : results) {
            System.out.printf(
                    "%d %d %d %d%n",
                    c.textId, c.lines, c.words, c.chars
            );
        }
    }
}

```

### Solution
```java
package Lab.Lab7.Task1;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class TextCounter {

    // Result holder
    public static class Counter {
        public final int textId;
        public final int lines;
        public final int words;
        public final int chars;

        public Counter(int textId, int lines, int words, int chars) {
            this.textId = textId;
            this.lines = lines;
            this.words = words;
            this.chars = chars;
        }

        @Override
        public String toString() {
            return "Counter{" +
                    "textId=" + textId +
                    ", lines=" + lines +
                    ", words=" + words +
                    ", chars=" + chars +
                    '}';
        }


    }

    
    public static Callable<Counter> getTextCounter(int textId, String text) {
        return () -> {
            if(text.isEmpty()){
                return new Counter(textId, 0,0,0);
            }

            int numLines = (int) text.lines().count();

            String [] words = text.split("\\s++");
            int numWords = words.length;
            int numChars = text.length();

            return new Counter(textId, numLines, numWords, numChars);
        };
    }

    

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();       // number of texts
        sc.nextLine();              // consume newline

        List<Callable<Counter>> tasks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int textId = sc.nextInt();
            sc.nextLine();          // consume newline

            int lines = sc.nextInt();   // number of lines for this text
            sc.nextLine();              // consume newline

            StringBuilder text = new StringBuilder();
            for (int j = 0; j < lines; j++) {
                text.append(sc.nextLine());
                if (j < lines - 1) {
                    text.append("\n");
                }
            }

            //TODO add a Callable<Counter> for each text read in the tasks list
            tasks.add(getTextCounter(textId, text.toString()));
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                

        //TODO invoke All tasks on the executor and create a List<Future<?>>
        List<Future<Counter>> futures = executor.invokeAll(tasks);
        

        List<Counter> results = new ArrayList<>();
        
        //TODO extract results from the List<Future>
        for (Future<Counter> future : futures) {
            results.add(future.get());
        }

        executor.shutdown();


        // Sorting by textId (important concept!)
        results.sort(Comparator.comparingInt(c -> c.textId));

        // Output (optional for debugging / demonstration)
        for (Counter c : results) {
            System.out.printf(
                    "%d %d %d %d%n",
                    c.textId, c.lines, c.words, c.chars
            );
        }
    }
}
```