package Lab.Lab2.Task6;

public class Queue<T> {
    private Node<T> first;
    private Node<T> last;

    public Queue() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(T element) {
        if (isEmpty()) {
            first = last = new Node<>(element, null);
            return;
        }
        Node<T> next = new Node<>(element, null);
        if (first == last) {
            first.setNext(next);
        } else {
            last.setNext(next);
        }
        last = next;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        T element;
        if (first == last) {
            element = first.getElement();
            first = last = null;
            return element;
        }

        element = first.getElement();
        first = first.getNext();
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return first.getElement();
    }

    public T inspect() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return last.getElement();
    }

    public int count(){
        Node<T> curr = first;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.getNext();
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> curr = first;
        while (curr != null) {
            sb.append(curr.getElement()).append(" ");
            curr = curr.getNext();
        }
        return sb.toString();
    }
}