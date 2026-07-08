package Lab.Lab1.Task2;

public class MovableCircle implements Movable {
    private int radius;
    private MovablePoint center;

    public MovableCircle(int radius, MovablePoint center) {
        this.radius = radius;
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }


    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        center.moveUp();
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        center.moveDown();
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        center.moveRight();
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        center.moveLeft();
    }

    @Override
    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    @Override
    public TYPE getType() {
        return TYPE.CIRCLE;
    }

    @Override
    public String toString() {
        return String.format("Movable circle with center coordinates (%d,%d) and radius %d", getCurrentXPosition(), getCurrentYPosition(), radius);
    }
}