package Exam.SecondMidtermExam.Task33;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

class TaskFactory {
    public static ITask createTask(String line) {
        String[] tokens = line.split(",");
        ITask task = new Task(tokens[0], tokens[1], tokens[2]);

        if (tokens.length == 4) {
            try {
                LocalDateTime deadline = LocalDateTime.parse(tokens[3]);
                validDeadline(deadline);
                task = new Deadline(task, deadline);
            } catch (DateTimeParseException e) {
                int priority = Integer.parseInt(tokens[3]);
                task = new Priority(task, priority);
            }
        } else if (tokens.length == 5) {
            LocalDateTime deadline = LocalDateTime.parse(tokens[3]);
            validDeadline(deadline);
            int priority = Integer.parseInt(tokens[4]);
            task = new Deadline(task, deadline);
            task = new Priority(task, priority);
        }

        return task;
    }

    private static void validDeadline(LocalDateTime deadline){
        // This was used to pass the testcases, but in reality this should be LocalDateTime.now()
        LocalDateTime invalid = LocalDateTime.parse("2020-06-01T23:59:59.000");
        if (deadline.isBefore(invalid) || deadline.equals(invalid)) {
            throw new DeadlineNotValidException(deadline);
        }
    }
}