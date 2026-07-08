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
  The users must be sorted by the **number of shapes** they have created in descending order (if that number is the same, then by the **sum of the areas** of their shapes).
  The shapes for a given user must be sorted by **perimeter in ascending order**.
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

class InvalidDimensionException extends RuntimeException {
    InvalidDimensionException() {
        super("Dimension 0 is not allowed!");
    }
}

class InvalidIDException extends RuntimeException {
    public InvalidIDException(String id) {
        super(String.format("ID %s is not valid", id));
    }
}

abstract class Shape implements Comparable<Shape> {
    protected String id;

    public Shape(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract double area();

    public abstract double perimeter();

    public abstract void scale(double coefficient);

    @Override
    public int compareTo(Shape other) {
        return Double.compare(this.area(), other.area());
    }
}

class Circle extends Shape {
    private double radius;

    public Circle(String id, double radius) {
        super(id);
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void scale(double coefficient) {
        radius *= coefficient;
    }

    @Override
    public String toString() {
        return String.format("Circle -> Radius: %.2f Area: %.2f Perimeter: %.2f", radius, area(), perimeter());
    }
}

class Square extends Shape{
    protected double base;

    public Square(String id, double base) {
        super(id);
        this.base = base;
    }

    @Override
    public double area() {
        return base * base;
    }

    @Override
    public double perimeter() {
        return 4 * base;
    }

    @Override
    public void scale(double coefficient) {
        base *= coefficient;
    }

    @Override
    public String toString() {
        return String.format("Square: -> Side: %.2f Area: %.2f Perimeter: %.2f", base, area(), perimeter());
    }
}

class Rectangle extends Square {
    private double height;

    public Rectangle(String id, double base, double height) {
        super(id, base);
        this.height = height;
    }

    @Override
    public double area() {
        return base * height;
    }

    @Override
    public double perimeter() {
        return 2 * base + 2 * height;
    }

    @Override
    public void scale(double coefficient) {
        base *= coefficient;
        height *= coefficient;
    }

    @Override
    public String toString() {
        return String.format("Rectangle: -> Sides: %.2f, %.2f Area: %.2f Perimeter: %.2f", base, height, area(), perimeter());
    }
}


class ShapeFactory {
    public static Shape createShape(String line){
        String [] tokens = line.split("\\s++");

        String type = tokens[0];
        String id = tokens[1];

        if(!checkId(id)){
            throw new InvalidIDException(id);
        }

        double base = Double.parseDouble(tokens[2]);


        if(base == 0){
            throw new InvalidDimensionException();
        }
        if(type.equals("1")){
            return new Circle(id, base);
        } else if (type.equals("2")) {
            return new Square(id, base);
        }else {
            double height = Double.parseDouble(tokens[3]);
            if(height == 0){
                throw new InvalidDimensionException();
            }
            return new Rectangle(id, base, height);
        }
    }

    private static boolean checkId(String id){
        if (id.length() != 6){
            return false;
        }

        for (int i = 0; i < id.length(); i++) {
            if(!Character.isLetterOrDigit(id.charAt(i))){
                return false;
            }
        }
        return true;
    }
}


class Canvas {
    private Set<Shape> shapes;

    public Canvas() {
        this.shapes = new TreeSet<>();
    }

    public void readShapes(InputStream is) {
        Scanner scanner = new Scanner(is);

        while (scanner.hasNextLine()) {
            try {
                shapes.add(ShapeFactory.createShape(scanner.nextLine()));
            } catch (InvalidIDException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDimensionException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }


     public void scaleShapes(String id, double coefficient) {
        shapes.stream()
                .filter(s -> s.getId().equals(id))
                .forEach(shape -> shape.scale(coefficient));
    }

    public void printAllShapes(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);

        shapes.forEach(pw::println);
        pw.flush();
    }

    public void printByUserId(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);

        Comparator<String> comparator = Comparator.comparing(
                        (String k) -> shapes.stream()
                                .filter(shape -> shape.getId().equals(k))
                                .count()
                ).reversed()
                .thenComparing(
                        k -> shapes.stream()
                                .filter(shape -> shape.getId().equals(k))
                                .mapToDouble(Shape::area)
                                .sum()
                );

        Map<String, Set<Shape>> grouped = shapes.stream()
                .collect(Collectors.groupingBy(
                        Shape::getId,
                        () -> new TreeMap<>(comparator),
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Shape::perimeter)))
                ));

        grouped.forEach((k, v) -> {
            pw.println("Shapes of user: " + k);
            v.forEach(pw::println);
        });
        pw.flush();
    }

    public void statistics(PrintStream os) {
        PrintWriter pw = new PrintWriter(os);
        
        DoubleSummaryStatistics dss = shapes.stream()
                .mapToDouble(Shape::area)
                .summaryStatistics();
        
        pw.printf("count: %d\n", dss.getCount());
        pw.printf("sum: %.2f\n", dss.getSum());
        pw.printf("min: %.2f\n", dss.getMin());
        pw.printf("average: %.2f\n", dss.getAverage());
        pw.printf("max: %.2f\n", dss.getMax());
        pw.flush();
    }
}
```