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

The first four methods of **Movable** (`moveUp`, `moveDown`, `moveRight`, `moveLeft`) should throw an exception of type **ObjectCanNotBeMovedException** if movement in the corresponding direction is not possible, meaning that the movement would cause the object to go outside the space defined in the **MovablesCollection** class. When moving objects of type **MovableCircle**, the circle is considered to have gone out of bounds if its **center** goes out of the defined space. It is allowed for part of the circle to go outside the space as long as the center remains within it. Handle these exceptions in the appropriate places. Check the test examples to see what messages should be printed when such an exception is caught, and implement the same behavior.

Define a class **MovablesCollection** that will store the following information:

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

Starter code:

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