package Exam.SecondMidtermExam.Task33;

import java.time.LocalDateTime;

class Task implements ITask {
    String category;
    String name;
    String description;

    public Task(String category, String name, String description) {
        this.category = category;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public LocalDateTime getDeadline() {
        return LocalDateTime.MAX;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("Task{name='%s', description='%s'}", name, description);
    }
}