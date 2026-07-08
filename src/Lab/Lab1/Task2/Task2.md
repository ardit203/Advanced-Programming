# Lab Exercise 1 - Advanced Programming

## Task 2

Define an interface **Movable** that will specify the basic properties of a movable object:

- movement upwards (`void moveUp()`)
- movement downwards (`void moveDown()`)
- movement to the right (`void moveRight()`)
- movement to the left (`void moveLeft()`)
- access to the current x, y coordinates of the object (`int getCurrentXPosition()` and `int getCurrentYPosition()`).

There are two types of movable objects: a **MovingPoint** and a **MovingCircle**. Define these two classes that implement the **Movable** interface.

In the **MovingPoint** class, the following information is stored:

- `x` and `y` coordinates (integers)
- `xSpeed` and `ySpeed`: the rate of movement of the point along the x and y directions (integers)

For the class, implement the following:

- A constructor with arguments:
  `MovablePoint(int x, int y, int xSpeed, int ySpeed)`
- The methods specified in the **Movable** interface
- A `toString` method that provides a representation of the object in the following format:
  `Movable point with coordinates (5,35)`

In the **MovingCircle** class, the following information is stored:

- the **radius** of the moving circle (integer)
- the **center** of the moving circle (an object of the **MovingPoint** class)

For the class, implement the following:

- A constructor with arguments:
  `MovableCircle(int radius, MovablePoint center)`
- The methods specified in the **Movable** interface
- A `toString` method that provides a representation of the object in the following format:
  `Movable circle with center coordinates (48,21) and radius 3`

The first four methods of **`Movable`** (`moveUp`, `moveDown`, `moveRight`, `moveLeft`) should throw an exception of type **`ObjectCanNotBeMovedException`** if movement in the corresponding direction is not possible, meaning that the movement would cause the object to go outside the space defined in the **`MovablesCollection`** class. When moving objects of type **`MovableCircle`**, the circle is considered to have gone out of bounds if its **center** goes out of the defined space. It is allowed for part of the circle to go outside the space as long as the center remains within it. Handle these exceptions in the appropriate places. Check the test examples to see what messages should be printed when such an exception is caught, and implement the same behavior.

Define a class **`MovablesCollection`** that will store the following information:

- an array of movable objects (`Movable[] movable`)
- a static variable for the **maximum value of the X coordinate** (the minimum is predefined as 0)
- a static variable for the **maximum value of the Y coordinate** (the minimum is predefined as 0)

Implement the following methods for the class:

- `MovablesCollection(int x_MAX, int y_MAX)` — constructor
- `void addMovableObject(Movable m)` — a method for adding a movable object to the collection of all movable objects. Before adding the object, you must check whether it can fit into the defined space, i.e., that it does not go outside the boundaries `0–X_MAX` for the x-coordinate and `0–Y_MAX` for the y-coordinate. If it is a moving circle, the entire circle must lie within the specified interval of values. If the movable object cannot be fitted into the space, throw an exception of type `MovableObjectNotFittableException`. You need to handle this exception in the appropriate place in the `main` method. Check the test examples to see what messages should be printed when such an exception is caught and implement the same behavior.
- `void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction)` — a method for moving the movable objects of the given `type` in the given `direction`. `TYPE` and `DIRECTION` are enumerations provided in the starter code. Depending on the direction specified in the argument, call the corresponding movement method.
- `toString()` — a method that returns a representation of the collection of movable objects in the following format:
  `Collection of movable objects with size [size of the collection]:`
  followed by, on a new line, the information for all movable objects in the collection.

### Starter code:

```java
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

public class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
            }
           
        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());


    }
}
```

### Solution:

```java
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}


class MovableObjectNotFittableException extends RuntimeException {
    public MovableObjectNotFittableException(int x, int y) {
        super(String.format("Movable point with center (%d,%d) can not be fitted into the collection"
                , x, y));
    }

    public MovableObjectNotFittableException(int x, int y, int radius) {
        super(String.format("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection"
                , x, y, radius));
    }
}



class ObjectCanNotBeMovedException extends RuntimeException {
    public ObjectCanNotBeMovedException(int x, int y) {
        super(String.format("Point (%d,%d) is out of bounds", x, y));
    }
}



class UnsupportedMoveException extends RuntimeException{

}



interface Movable {
    public void moveUp();

    public void moveDown();

    public void moveRight();

    public void moveLeft();

    public int getCurrentXPosition();

    public int getCurrentYPosition();

    public TYPE getType();
}


class MovablePoint implements Movable {
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
    public TYPE getType() {
        return TYPE.POINT;
    }

    @Override
    public String toString() {
        return String.format("Movable point with coordinates (%d,%d)\n", x, y);
    }
}


class MovableCircle implements Movable {
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


interface Move {
    void move(Movable movable);
}

class MoveFactory {
    private static final Move MOVE_UP = m -> m.moveUp();
    private static final Move MOVE_DOWN = m -> m.moveDown();
    private static final Move MOVE_RIGHT = m -> m.moveRight();
    private static final Move MOVE_LEFT = m -> m.moveLeft();

    public static Move move(TYPE type, DIRECTION direction, Movable movable) {
        if (type == movable.getType()) {
            if (direction == DIRECTION.UP) {
                return MOVE_UP;
            } else if (direction == DIRECTION.DOWN) {
                return MOVE_DOWN;
            } else if (direction == DIRECTION.RIGHT) {
                return MOVE_RIGHT;
            } else if (direction == DIRECTION.LEFT) {
                return MOVE_LEFT;
            }
        }
        throw new UnsupportedMoveException();
    }
}



class MovablesCollection {
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
        if (m.getType() == TYPE.CIRCLE) {
            MovableCircle mc = (MovableCircle) m;
            int radius = mc.getRadius();
            if (x + radius > x_MAX || x - radius < x_MIN || y + radius > y_MAX || y - radius < y_MIN) {
                throw new MovableObjectNotFittableException(x, y, radius);
            }
        }
        movable[size++] = m;
    }


    public void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) {
        for (int i = 0; i < size; i++) {
            try {
                Move moveInDirection = MoveFactory.move(type, direction, movable[i]);
                moveInDirection.move(movable[i]);
            } catch (ObjectCanNotBeMovedException e) {
                System.out.println(e.getMessage());
            } catch (UnsupportedMoveException e) {
                continue;
            }
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

public class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                try {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                try {
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            }
           
        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());


    }
}
```


### Solution 2
```java
import java.util.Arrays;
import java.util.Scanner;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

class MovableObjectNotFittableException extends Exception {
    public MovableObjectNotFittableException(int x, int y, int radius) {
        super(String.format("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection", x, y, radius));
    }

    public MovableObjectNotFittableException(int x, int y) {
        super(String.format(""));
    }
}

class ObjectCanNotBeMovedException extends Exception {
    public ObjectCanNotBeMovedException(int x, int y) {
        super(String.format("Point (%d,%d) is out of bounds", x, y));
    }
}

interface Movable {
    void moveUp() throws ObjectCanNotBeMovedException;

    void moveDown() throws ObjectCanNotBeMovedException;

    void moveRight() throws ObjectCanNotBeMovedException;

    void moveLeft() throws ObjectCanNotBeMovedException;

    int getCurrentXPosition();

    int getCurrentYPosition();

    TYPE getType();
}

class MovablePoint implements Movable {
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

class MovableCircle implements Movable {
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

class MovablesCollection {
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

public class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                try {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                try {
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");

        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);

        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");

        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);

        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");

        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);

        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");

        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);

        System.out.println(collection.toString());


    }


}
```