package Lab.Lab1.Task2;

public class MovableObjectNotFittableException extends RuntimeException {
    public MovableObjectNotFittableException(int x, int y) {
        super(String.format("Movable point with center (%d,%d) can not be fitted into the collection"
                , x, y));
    }

    public MovableObjectNotFittableException(int x, int y, int radius) {
        super(String.format("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection"
                , x, y, radius));
    }
}