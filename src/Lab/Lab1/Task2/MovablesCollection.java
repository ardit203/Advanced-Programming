package Lab.Lab1.Task2;

import java.util.Arrays;

public class MovablesCollection {
    public static int x_MIN = 0;
    public static int y_MIN = 0;
    public static int x_MAX = 0;
    public static int y_MAX = 0;
    private Movable[] movables;
    private int size;
    private int capacity;

    public MovablesCollection(int x_MAX, int y_MAX) {
        MovablesCollection.x_MAX = x_MAX;
        MovablesCollection.y_MAX = y_MAX;
        this.size = 0;
        this.capacity = 10;
        this.movables = new Movable[capacity];
    }

    public static void setxMax(int max) {
        x_MAX = max;
    }

    public static void setyMax(int max) {
        y_MAX = max;
    }

    private void resize() {
        capacity += 10;
        movables = Arrays.copyOf(movables, capacity);
    }

    public void addMovableObject(Movable m) throws MovableObjectNotFittableException {
        canFit(m);
        if (size == capacity) {
            resize();
        }
        movables[size++] = m;
    }

    private void canFit(Movable m) throws MovableObjectNotFittableException {
        int x = m.getCurrentXPosition();
        int y = m.getCurrentYPosition();

        if (m.getType() == TYPE.CIRCLE) {
            MovableCircle mc = (MovableCircle) m;
            int radius = mc.getRadius();

            if (x - radius < x_MIN || x + radius > x_MAX || y - radius < y_MIN || y + radius > y_MAX) {
                throw new MovableObjectNotFittableException(m.getCurrentXPosition(), m.getCurrentYPosition(), radius);
            }
        } else {
            if (x < x_MIN || x > x_MAX || y < y_MIN || y > y_MAX) {
                throw new MovableObjectNotFittableException(m.getCurrentXPosition(), m.getCurrentYPosition());
            }
        }

    }

    public void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) {
        for (int i = 0; i < size; i++) {
            if (movables[i].getType() == type) {
                try {
                    move(movables[i], direction);
                } catch (ObjectCanNotBeMovedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void move(Movable m, DIRECTION direction) throws ObjectCanNotBeMovedException {
        switch (direction) {
            case DOWN:
                m.moveDown();
                break;
            case UP:
                m.moveUp();
                break;
            case LEFT:
                m.moveLeft();
                break;
            case RIGHT:
                m.moveRight();
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Collection of movable objects with size ").append(size).append(":\n");
        for (int i = 0; i < size; i++) {
            sb.append(movables[i]).append("\n");
        }
        return sb.toString();
    }
}