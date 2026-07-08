package Exam.SecondMidtermExam.Task33;

import java.time.LocalDateTime;

class Priority extends TaskDecorator {
    private int priority;

    public Priority(ITask task, int priority) {
        super(task);
        this.priority = priority;
    }

    @Override
    public LocalDateTime getDeadline() {
        return task.getDeadline();
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("%s, priority=%d}", task.toString().replace("}", ""), priority);
    }
}