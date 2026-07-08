package Exam.SecondMidtermExam.Task33;

import java.time.LocalDateTime;

class Deadline extends TaskDecorator {
    LocalDateTime deadline;

    public Deadline(ITask task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public LocalDateTime getDeadline() {
        return deadline;
    }

    @Override
    public int getPriority() {
        return task.getPriority();
    }

    @Override
    public String toString() {
        return String.format("%s, deadline=%s}", task.toString().replace("}", ""), deadline);
    }
}