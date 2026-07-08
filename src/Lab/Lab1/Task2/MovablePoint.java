package Lab.Lab1.Task2;

public class MovablePoint implements Movable {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }


    @Override
    public String toString() {
        return String.format("Movable point with coordinates (%d,%d)", x, y);
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        if (y + ySpeed > MovablesCollection.y_MAX) {
            throw new ObjectCanNotBeMovedException(x, y + ySpeed);
        }
        y += ySpeed;
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        if (y - ySpeed < MovablesCollection.y_MIN) {
            throw new ObjectCanNotBeMovedException(x, y - ySpeed);
        }
        y -= ySpeed;
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        if (x + xSpeed > MovablesCollection.x_MAX) {
            throw new ObjectCanNotBeMovedException(x + xSpeed, y);
        }
        x += xSpeed;
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if (x - xSpeed < MovablesCollection.x_MIN) {
            throw new ObjectCanNotBeMovedException(x - xSpeed, y);
        }
        x -= xSpeed;
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public TYPE getType() {
        return TYPE.POINT;
    }
}