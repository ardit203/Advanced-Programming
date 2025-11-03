package Lab.Lab1.Task2;


import java.util.function.Consumer;
import java.util.function.Predicate;

public class MovablesCollection {
    Movable[] movable;
    int size;
    private static int x_MAX = 0;
    private static int y_MAX = 0;
    private final static int x_MIN = 0;
    private final static int y_MIN = 0;

    public MovablesCollection(int x_MAX, int y_MAX) {
        MovablesCollection.x_MAX = x_MAX;
        MovablesCollection.y_MAX = y_MAX;
        movable = new Movable[MovablesCollection.x_MAX * MovablesCollection.y_MAX];
        this.size = 0;
    }

    public void addMovableObject(Movable m) {
        int x = m.getCurrentXPosition();
        int y = m.getCurrentYPosition();
        if (x > x_MAX || x < x_MIN || y > y_MAX || y < y_MIN) {
            throw new MovableObjectNotFittableException(x, y);
        }
        if (m instanceof MovableCircle) {
            MovableCircle mc = (MovableCircle) m;
            int radius = mc.getRadius();
            if (x + radius > x_MAX || x - radius < x_MIN || y + radius > y_MAX || y - radius < y_MIN) {
                throw new MovableObjectNotFittableException(x, y, radius);
            }
        }
        movable[size++] = m;
    }

    public void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) {
        Predicate<Movable> predicate = getPredicate(type);
        Consumer<Movable> consumer = getConsumer(direction);
        for (int i = 0; i < size; i++) {
            if (predicate.test(movable[i])) {
                try {
                    consumer.accept(movable[i]);
                } catch (ObjectCanNotBeMovedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private Consumer<Movable> getConsumer(DIRECTION direction) {
        if (direction == DIRECTION.LEFT) {
            return m -> m.moveLeft();
        } else if (direction == DIRECTION.RIGHT) {
            return m -> m.moveRight();
        } else if (direction == DIRECTION.UP) {
            return m -> m.moveUp();
        } else {
            return m -> m.moveDown();
        }
    }

    private Predicate<Movable> getPredicate(TYPE type) {
        if (type == TYPE.POINT) {
            return p -> p instanceof MovablePoint;
        } else {
            return p -> p instanceof MovableCircle;
        }
    }


    public static int getxMAX() {
        return x_MAX;
    }

    public static int getyMAX() {
        return y_MAX;
    }

    public static int getxMIN() {
        return x_MIN;
    }

    public static int getyMIN() {
        return y_MIN;
    }

    public static void setxMax(int value) {
        x_MAX = value;
    }

    public static void setyMax(int value) {
        y_MAX = value;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Collection of movable objects with size ").append(size).append(":\n");
        for (int i = 0; i < size; i++) {
            sb.append(movable[i]);
        }
        return sb.toString();
    }
}