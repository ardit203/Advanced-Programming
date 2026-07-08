Write a class `TaskManager` that will be used for managing tasks of a given user. For the class, implement the following methods:

* `readTasks(InputStream inputStream)` – method for reading the user’s tasks, where each task is in the following format: `[category][task_name],[description],[deadline],[priority]`. The deadline and the priority are optional fields.

    * A given task must not be allowed to have a deadline that has already passed. In such a case, an exception of type `DeadlineNotValidException` should be thrown. The exception should be caught in an appropriate place so that the reading of the remaining tasks is not interrupted!!!

* `void printTasks(OutputStream os, boolean includePriority, boolean includeCategory)` – method for printing the tasks.

    * If `includeCategory` is `true`, the tasks should be printed grouped by categories; otherwise, all entered tasks are printed.
    * If `includePriority` is `true`, the tasks should be printed sorted by priority (where 1 is the highest priority), and the tasks that have no priority or have the same priority are sorted in ascending order according to the time distance between the deadline and the current date, i.e. the tasks with a deadline closest to today’s date are printed first.
    * If `includePriority` is `false`, they are printed in ascending order according to the time distance between the deadline and the current date.
    * When printing the tasks, the default option for `toString` is used (if you are working in IntelliJ), with the note that you must pay attention to the names of the variables.

**Bonus:** Using software design patterns for the representation of the tasks and for their creation.

### Starter code
```java
public class TasksManagerTest {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        System.out.println("Tasks reading");
        manager.readTasks(System.in);
        System.out.println("By categories with priority");
        manager.printTasks(System.out, true, true);
        System.out.println("-------------------------");
        System.out.println("By categories without priority");
        manager.printTasks(System.out, false, true);
        System.out.println("-------------------------");
        System.out.println("All tasks without priority");
        manager.printTasks(System.out, false, false);
        System.out.println("-------------------------");
        System.out.println("All tasks with priority");
        manager.printTasks(System.out, true, false);
        System.out.println("-------------------------");

    }
}
```

### Solution
```java
class CosineSimilarityCalculator {
    public static double cosineSimilarity (Collection<Integer> c1, Collection<Integer> c2) {
        int [] array1;
        int [] array2;
        array1 = c1.stream().mapToInt(i -> i).toArray();
        array2 = c2.stream().mapToInt(i -> i).toArray();
        double up = 0.0;
        double down1=0, down2=0;

        for (int i=0;i<c1.size();i++) {
            up+=(array1[i] * array2[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down1+=(array1[i]*array1[i]);
        }

        for (int i=0;i<c1.size();i++) {
            down2+=(array2[i]*array2[i]);
        }

        return up/(Math.sqrt(down1)*Math.sqrt(down2));
    }
}

public class TextProcessorTest {

    public static void main(String[] args) {
        TextProcessor textProcessor = new TextProcessor();

        textProcessor.readText(System.in);

        System.out.println("===PRINT VECTORS===");
        textProcessor.printTextsVectors(System.out);

        System.out.println("PRINT FIRST 20 WORDS SORTED ASCENDING BY FREQUENCY ");
        textProcessor.printCorpus(System.out,  20, true);

        System.out.println("PRINT FIRST 20 WORDS SORTED DESCENDING BY FREQUENCY");
        textProcessor.printCorpus(System.out, 20, false);

        System.out.println("===MOST SIMILAR TEXTS===");
        textProcessor.mostSimilarTexts(System.out);
    }
}
```