package Lab.Lab2.Task6;

public class EmptyQueueException extends RuntimeException {
    public EmptyQueueException() {
        super("Queue is empty");
    }
}
