It is required to define two task schedulers that implement the interface `TaskScheduler`.
`TaskScheduler` is a generic interface for scheduling tasks (`Task`) with a single method `schedule`, which receives an array of tasks and is expected to return a list of those same tasks, scheduled according to the scheduling criteria.

A task (`Task`) is an interface that implements a method which returns the execution order number of the task.
There are two types of tasks (`TimedTask` and `PriorityTask`).
The order number of a `TimedTask` is defined by its execution time (`time`), while for a `PriorityTask` it is defined by the priority of execution (`priority`).

The first scheduler schedules the tasks in such a way that it sorts them by their order number.
Its implementation needs to be provided using an anonymous class (in the method `getOrdered` of the class Schedulers).

The second scheduler preserves the order of the received tasks, but filters (removes) all tasks with an order number greater than a given threshold (`order`).
Its implementation must be provided using a lambda expression (in the method `getFiltered` of the class Schedulers).

Scheduled tasks are executed using the generic class `TaskRunner`.
For this class, it is only necessary to define the type parameter.

### Starter code
```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * I Partial exam 2016
 */
public class TaskSchedulerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Task[] timeTasks = new Task[n];
        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            timeTasks[i] = new TimedTask(x);
        }
        n = scanner.nextInt();
        Task[] priorityTasks = new Task[n];
        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            priorityTasks[i] = new PriorityTask(x);
        }
        Arrays.stream(priorityTasks).forEach(System.out::println);
        TaskRunner<Task> runner = new TaskRunner<>();
        System.out.println("=== Ordered tasks ===");
        System.out.println("Timed tasks");
        runner.run(Schedulers.getOrdered(), timeTasks);
        System.out.println("Priority tasks");
        runner.run(Schedulers.getOrdered(), priorityTasks);
        int filter = scanner.nextInt();
        System.out.printf("=== Filtered time tasks with order less then %d ===\n", filter);
        runner.run(Schedulers.getFiltered(filter), timeTasks);
        System.out.printf("=== Filtered priority tasks with order less then %d ===\n", filter);
        runner.run(Schedulers.getFiltered(filter), priorityTasks);
        scanner.close();
    }
}

class TaskRunner ??? {
    public void run(TaskScheduler???? scheduler, T[] tasks) {
        List<T> order = scheduler.schedule(tasks);
        order.forEach(System.out::println);
    }
}

interface TaskScheduler ???

interface Task {
    //dopolnete ovde
}

class PriorityTask implements Task {
    private final int priority;

    public PriorityTask(int priority) {
        this.priority = priority;
    }

    
    @Override
    public String toString() {
        return String.format("PT -> %d", getOrder());
    }
}

class TimedTask implements Task {
    private final int time;

    public TimedTask(int time) {
        this.time = time;
    }

    
    @Override
    public String toString() {
        return String.format("TT -> %d", getOrder());
    }
}

class Schedulers {
    public static ??? TaskScheduler<T> getOrdered() {
        
        // (annonimous class)
        
    }

    public static ??? TaskScheduler<T> getFiltered(int order) {
        
        // (lambda expression)
        
    }
}
```

### Solution
```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * I Partial exam 2016
 */
public class TaskSchedulerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Task[] timeTasks = new Task[n];
        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            timeTasks[i] = new TimedTask(x);
        }
        n = scanner.nextInt();
        Task[] priorityTasks = new Task[n];
        for (int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            priorityTasks[i] = new PriorityTask(x);
        }
        Arrays.stream(priorityTasks).forEach(System.out::println);
        TaskRunner<Task> runner = new TaskRunner<>();
        System.out.println("=== Ordered tasks ===");
        System.out.println("Timed tasks");
        runner.run(Schedulers.getOrdered(), timeTasks);
        System.out.println("Priority tasks");
        runner.run(Schedulers.getOrdered(), priorityTasks);
        int filter = scanner.nextInt();
        System.out.printf("=== Filtered time tasks with order less then %d ===\n", filter);
        runner.run(Schedulers.getFiltered(filter), timeTasks);
        System.out.printf("=== Filtered priority tasks with order less then %d ===\n", filter);
        runner.run(Schedulers.getFiltered(filter), priorityTasks);
        scanner.close();
    }
}

class TaskRunner<T extends Task> {
    public void run(TaskScheduler<T> scheduler, T[] tasks) {
        List<T> order = scheduler.schedule(tasks);
        order.forEach(System.out::println);
    }
}

interface TaskScheduler<T extends Task> {
    List<T> schedule(T[] tasks);
}

interface Task {
    int getOrder();
}

class PriorityTask implements Task {
    private final int priority;

    public PriorityTask(int priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return String.format("PT -> %d", getOrder());
    }

    @Override
    public int getOrder() {
        return priority;
    }
}

class TimedTask implements Task {
    private final int time;

    public TimedTask(int time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return String.format("TT -> %d", getOrder());
    }

    @Override
    public int getOrder() {
        return time;
    }
}

class Schedulers {
    public static <T extends Task> TaskScheduler<T> getOrdered() {
        return new TaskScheduler<T>() {
            @Override
            public List<T> schedule(T[] tasks) {
                return Arrays.stream(tasks)
                        .sorted(Comparator.comparingInt(Task::getOrder))
                        .collect(Collectors.toList());
            }
        };
    }

    public static <T extends Task> TaskScheduler<T> getFiltered(int order) {
        return tasks -> Arrays.stream(tasks)
                .filter(t -> t.getOrder() <= order)
                .collect(Collectors.toList());
    }
}
```