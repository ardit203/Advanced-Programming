package Lab.Lab1.Task2;

public class ObjectCanNotBeMovedException extends Exception {
    public ObjectCanNotBeMovedException(int x, int y) {
        super(String.format("Point (%d,%d) is out of bounds", x, y));
    }
}