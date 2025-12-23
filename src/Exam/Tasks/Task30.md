You need to write the class **`Canvas`** in which shapes of different types will be stored.
For each shape it must be possible to obtain information about its **area** and **perimeter**, as well as to allow the shape to be scaled by some coefficient.
In the class **`Canvas`** the following must be implemented:

* **default constructor**
* **`void readShapes(InputStream is)`** – method for reading information about the shapes from an input stream.
* The information for each shape is given on a single line. When reading the shapes, first an integer is read
  (**1 = circle / 2 = square / 3 = rectangle**), then the ID of the user who created the shape is read, and then:

    * if it is a circle/square – one decimal number is read for the radius/side of the circle/square;
    * if it is a rectangle – two decimal numbers are read for the width and height of the rectangle.
* The user ID must be a string with length 6, where no special characters are allowed (only letters and digits).
  If some ID is not valid, an exception of type **`InvalidIDException`** should be thrown when creating the shape, and you must handle it inside the function **`readShapes`**, i.e. an invalid ID must **not** cause the reading of shapes to stop.
* A dimension of a shape must not be 0. In such a case, an exception of type **`InvalidDimensionException`** should be thrown.
  This exception **must stop** any further reading of the remaining shapes.
* **`void scaleShapes(String userID, double coef)`** – method that will scale all shapes created by the user `userID` by the coefficient `coef` (it will multiply all dimensions of those shapes by that coefficient).
* **`void printAllShapes(OutputStream os)`** – method that will print all shapes to the output stream, sorted by their **area in ascending order**.
* **`void printByUserId(OutputStream os)`** – method that will print the shapes grouped by the user that created them.
  The users must be sorted by the **number of shapes** they have created (if that number is the same, then by the **sum of the areas** of their shapes).
  The shapes for a given user must be sorted by **perimeter in descending order**.
* **`void statistics(OutputStream os)`** – method that will print statistics for the areas of all shapes in the collection (**min, max, average, sum, count**).

**Note:**

* To achieve the required precision, always use **`double`** for all decimal numbers!
* It is forbidden to use **`.sorted()`** to sort the shapes. You may use this method **only** to sort the *groups of users* by number of shapes in the method **`printByUserId`**.


### Starter code
```java
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CanvasTest {

    public static void main(String[] args) {
        Canvas canvas = new Canvas();

        System.out.println("READ SHAPES AND EXCEPTIONS TESTING");
        canvas.readShapes(System.in);
      
        System.out.println("BEFORE SCALING");
        canvas.printAllShapes(System.out);
        canvas.scaleShapes("123456", 1.5);
        System.out.println("AFTER SCALING");
        canvas.printAllShapes(System.out);

        System.out.println("PRINT BY USER ID TESTING");
        canvas.printByUserId(System.out);

        System.out.println("PRINT STATISTICS");
        canvas.statistics(System.out);
    }
}
```

### Solution
```java
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

class InvalidDimensionException extends RuntimeException {
    public InvalidDimensionException(int dimension) {
        super(String.format("Dimension %d is not allowed!", dimension));
    }
}


class InvalidIDException extends RuntimeException{
    public InvalidIDException(String id) {
        super(String.format("ID %s is not valid", id));
    }
}


class ShapeFactory {
    public static Shape create(String line) {
        String[] tokens = line.split("\\s++");

        String type = tokens[0];
        String id = tokens[1];
        if (idFailed(id)) {
            throw new InvalidIDException(id);
        }

        if (type.equals("1")) {
            double radius = Double.parseDouble(tokens[2]);
            checkLengths(radius);
            return new Circle(id, radius);
        } else if (type.equals("2")) {
            double side = Double.parseDouble(tokens[2]);
            checkLengths(side);
            return new Square(id, side);
        } else {
            double width = Double.parseDouble(tokens[2]);
            double height = Double.parseDouble(tokens[3]);
            checkLengths(width);
            checkLengths(height);
            return new Rectangle(id, width, height);
        }


    }

    public static boolean idFailed(String id) {
        return !id.matches("[a-zA-Z0-9]{6}") || id.length() != 6;
    }

    public static void checkLengths(double length) {
        if (length <= 0) {
            throw new InvalidDimensionException((int) length);
        }
    }
}


abstract class Shape implements Comparable<Shape>{
    private String id;

    public Shape(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract double perimeter();

    public abstract double area();

    public abstract void scale(double coef);
    
    @Override
    public int compareTo(Shape other){
        return Comparator.comparingDouble(Shape::perimeter).compare(this, other);
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String id, double radius) {
        super(id);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double perimeter() {
        return Math.PI * 2 * radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double coef) {
        this.radius *= coef;
    }
    
    @Override
    public String toString() {
        return String.format("Circle -> Radius: %.2f Area: %.2f Perimeter: %.2f"
                ,radius, area(), perimeter());
    }
}


class Square extends Shape {
    private double side;

    public Square(String id, double side) {
        super(id);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double perimeter() {
        return 4 * side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public void scale(double coef) {
        side *= coef;
    }
    @Override
    public String toString() {
        return String.format("Square: -> Side: %.2f Area: %.2f Perimeter: %.2f"
                , side, area(), perimeter());
    }
}


class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String id, double width, double height) {
        super(id);
        this.width = width;
        this.height = height;
    }

    @Override
    public double perimeter() {
        return 2 * width + 2 * height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public void scale(double coef) {
        width *= coef;
        height *= coef;
    }
    @Override
    public String toString() {
        return String.format("Rectangle: -> Sides: %.2f, %.2f Area: %.2f Perimeter: %.2f"
                , width, height, area(), perimeter());
    }
}







class Canvas {
    private Set<Shape> shapes;

    public Canvas() {
        this.shapes = new TreeSet<>(Comparator.comparingDouble(Shape::area));
    }

    public void readShapes(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            br.lines().forEach(l -> {
                try {
                    shapes.add(ShapeFactory.create(l));
                } catch (InvalidIDException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (InvalidDimensionException e) {
            System.out.println(e.getMessage());
        }

    }

    public void scaleShapes(String id, double coef) {
        shapes.stream()
                .filter(s -> s.getId().equals(id))
                .forEach(s -> s.scale(coef));
    }


    public void printAllShapes(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        shapes.forEach(pw::println);
        pw.flush();
    }

    public void printByUserId(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        Map<String, Set<Shape>> shapesMap = shapes.stream()
                .collect(Collectors.groupingBy(
                        Shape::getId,
                        Collectors.toCollection(TreeSet::new)
                ));

        shapesMap.entrySet().stream()
                .sorted(Comparator.comparingDouble((Map.Entry<String, Set<Shape>> e) -> e.getValue().size()).reversed()
                        .thenComparing(e -> e.getValue().stream().mapToDouble(Shape::area).sum()))
                .forEach(
                        e -> {
                            pw.printf("Shapes of user: %s\n", e.getKey());
                            e.getValue().forEach(pw::println);
                        }
                );
        pw.flush();

    }


    public void statistics(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        int count = shapes.size();
        double sum = shapes.stream().mapToDouble(Shape::area).sum();
        double min = shapes.stream().mapToDouble(Shape::area).min().orElse(0);
        double max = shapes.stream().mapToDouble(Shape::area).max().orElse(0);
        double avg = shapes.stream().mapToDouble(Shape::area).average().orElse(0);

        pw.printf("count: %d\n", count);
        pw.printf("sum: %.2f\n", sum);
        pw.printf("min: %.2f\n", min);
        pw.printf("average: %.2f\n", avg);
        pw.printf("max: %.2f\n", max);

        pw.flush();
    }


}



public class CanvasTest {

    public static void main(String[] args) {
        Canvas canvas = new Canvas();

        System.out.println("READ SHAPES AND EXCEPTIONS TESTING");
        canvas.readShapes(System.in);
      
        System.out.println("BEFORE SCALING");
        canvas.printAllShapes(System.out);
        canvas.scaleShapes("123456", 1.5);
        System.out.println("AFTER SCALING");
        canvas.printAllShapes(System.out);

        System.out.println("PRINT BY USER ID TESTING");
        canvas.printByUserId(System.out);

        System.out.println("PRINT STATISTICS");
        canvas.statistics(System.out);
    }
}
```