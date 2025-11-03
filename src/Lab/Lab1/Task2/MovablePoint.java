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
    public void moveUp() {
        if (y + ySpeed > MovablesCollection.getyMAX()) {
            throw new ObjectCanNotBeMovedException(x, y + ySpeed);
        }
        this.y += ySpeed;
    }

    @Override
    public void moveDown() {
        if (y - ySpeed < MovablesCollection.getyMIN()) {
            throw new ObjectCanNotBeMovedException(x, y - ySpeed);
        }
        this.y -= ySpeed;
    }

    @Override
    public void moveRight() {
        if (x + xSpeed > MovablesCollection.getxMAX()) {
            throw new ObjectCanNotBeMovedException(x + xSpeed, y);
        }
        this.x += xSpeed;
    }

    @Override
    public void moveLeft() {
        if (x - xSpeed < MovablesCollection.getxMIN()) {
            throw new ObjectCanNotBeMovedException(x - xSpeed, y);
        }
        this.x -= xSpeed;
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
    public String toString() {
        return String.format("Movable point with coordinates (%d,%d)\n", x, y);
    }
}