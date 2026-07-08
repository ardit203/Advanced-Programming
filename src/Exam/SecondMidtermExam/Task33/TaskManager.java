package Exam.SecondMidtermExam.Task33;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class TaskManager {

    private Map<String, List<ITask>> tasks;

    public TaskManager() {
        this.tasks = new TreeMap<>();
    }

    public void readTasks(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        tasks = br.lines()
                .map(line -> {
                    try {
                        return TaskFactory.createTask(line);
                    } catch (DeadlineNotValidException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
//                .map(TaskFactory::createTask)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        ITask::getCategory,
                        TreeMap::new,
                        Collectors.toList()
                ));
    }

    public void printTasks(OutputStream os, boolean includePriority, boolean includeCategory) {
        PrintWriter pw = new PrintWriter(os);
        Comparator<ITask> compareByDeadline = Comparator.comparing(t -> Duration.between(LocalDateTime.now(), t.getDeadline()));
        Comparator<ITask> compareByPriority = Comparator.comparing(ITask::getPriority).thenComparing(compareByDeadline);

        Comparator<ITask> comparator = includePriority ? compareByPriority : compareByDeadline;

        if (includeCategory) {
            tasks.forEach((k, v) -> {
                pw.println(k.toUpperCase());
                v.stream()
                        .sorted(comparator)
                        .forEach(pw::println);
            });
        } else {
            tasks.values()
                    .stream()
                    .flatMap(Collection::stream)
                    .sorted(comparator)
                    .forEach(pw::println);
        }
        pw.flush();


    }
}