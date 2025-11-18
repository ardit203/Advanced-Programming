package Lab.Lab1.Task2;


public class MovableCircle implements Movable {
    private int radius;
    private MovablePoint point;

    public MovableCircle(int radius, MovablePoint point) {
        this.radius = radius;
        this.point = point;
    }



    @Override
    public void moveUp() {
        point.moveUp();
    }

    @Override
    public void moveDown() {
        point.moveDown();
    }

    @Override
    public void moveRight() {
        point.moveRight();
    }

    @Override
    public void moveLeft() {
        point.moveLeft();
    }

    @Override
    public int getCurrentXPosition() {
        return point.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return point.getCurrentYPosition();
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public TYPE getType() {
        return TYPE.CIRCLE;
    }

    @Override
    public String toString() {
        return String
                .format("Movable circle with center coordinates (%d,%d) and radius %d\n",
                        point.getCurrentXPosition(), point.getCurrentYPosition(), radius);
    }
}