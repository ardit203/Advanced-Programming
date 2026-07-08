package Exam.SecondMidtermExam.Task33;

abstract class TaskDecorator implements ITask {
    protected ITask task;

    public TaskDecorator(ITask task) {
        this.task = task;
    }

    @Override
    public String getCategory() {
        return task.getCategory();
    }
}