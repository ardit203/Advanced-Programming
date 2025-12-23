# **First Midterm Exam – Problem Statement**

Define a class `ShapesApplication` where you'll keep information about multiple windows on which geometric images (in different shape - square and circle) are drawn.

For the class you need to define and implement:

- `ShapesApplication(double maxArea)` - constructor with one argument which represents the maximum allowed area of a shape that can be drawn on the windows.
- `void readCanvases (InputStream inputStream)` - method that will read info about multiple windows from input stream. Each line of the data stream represents one window and it's in the format `canvas_id type_1 size_1 type_2 size_2 type_3 size_3 …. type_n size_n` where canvas_id is the ID of the window and after the ID there are unknown number of pairs of data for the shapes. Each pair has its type (character `S` = square, `C` = circle) and the side of the side of the square or the size of the radius of the circle.
- When adding the geometric images on the window, the creation and addition of a window which contains a shape with area greater than the maximum area, should not be allowed. This should be done via exception of type `InvalidCanvasException`. Throwing an exception of this type should not stop the reading of the data from the input stream. When catching the exception, the following message should be printed: `Canvas [canvas_id] has a shape with area larger than [max_area]`.
- `void printCanvases(OutputStream os)` - method that will print to output stream the information for all the windows in the application. The windows should be sorted in descending order by the sum of the areas of the geometric shapes in them. Each window should be printed in the following format: `ID total_shapes total_circles total_squares min_area max_area average_area`.

For the value of `PI` use `Math.PI`. Use double for better precision of the decimal numbers.


### Starter code

```java
public class Shapes2Test {

    public static void main(String[] args) {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);


    }
}
```


### Solution
```java
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;




class InvalidCanvasException extends RuntimeException {
    public InvalidCanvasException(String id, double maxArea) {
        super(String.format("Canvas %s has a shape with area larger than %.2f", id, maxArea));
    }
}




abstract class Shape {
    private int length;

    public Shape(int length){
        this.length = length;
    }

    public abstract double area();

    public int getLength(){
        return length;
    }
}




class Square extends Shape{

    public Square(int length){
        super(length);
    }

    @Override
    public double area() {
        return getLength()*getLength();
    }
}




class Circle extends Shape {

    public Circle(int length) {
        super(length);
    }

    @Override
    public double area() {
        return Math.PI * getLength() * getLength();
    }
}




class ShapeFactory {
    public static Canvas create(String line, double maxArea) {
        String[] tokens = line.split("\\s++");

        String id = tokens[0];
        List<Shape> shapes = new ArrayList<>();
        for (int i = 1; i < tokens.length - 1; i += 2) {
            int length = Integer.parseInt(tokens[i + 1]);
            Shape shape;

            if (tokens[i].equals("C")) {
                shape = new Circle(length);
            } else {
                shape = new Square(length);
            }

            if (shape.area() > maxArea) {
                throw new InvalidCanvasException(id, maxArea);
            }
            
            shapes.add(shape);
        }
        return new Canvas(id, shapes);
    }
}




class Canvas {
    private String canvasId;
    private List<Shape> shapes;

    public Canvas(String id, List<Shape> shapes) {
        this.canvasId = id;
        this.shapes = shapes;
    }

    public double sumArea() {
        return shapes.stream().mapToDouble(Shape::area).sum();
    }

    public int countShapesOfType(Class<?> type) {
        return (int) shapes.stream().filter(type::isInstance).count();
    }

    public double minArea() {
        return shapes.stream().mapToDouble(Shape::area).min().orElse(0);
    }

    public double maxArea() {
        return shapes.stream().mapToDouble(Shape::area).max().orElse(0);
    }

    public double avgArea() {
        return shapes.stream().mapToDouble(Shape::area).average().orElse(0);
    }

    @Override
    public String toString() {
        int circlesCount = countShapesOfType(Circle.class);
        int squaresCount = countShapesOfType(Square.class);
        double max = maxArea();
        double min = minArea();
        double avg = avgArea();

        return String.format("%s %d %d %d %.2f %.2f %.2f", canvasId, shapes.size(), circlesCount, squaresCount, min, max, avg);
    }
}




class ShapesApplication {
    private double maxArea;
    private List<Canvas> canvases;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
        this.canvases = new ArrayList<>();
    }

    public void readCanvases(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        
        canvases = br.lines().map(l -> {
                    try {
                        return ShapeFactory.create(l, maxArea);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void printCanvases(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);

        canvases.stream().sorted(Comparator.comparingDouble(Canvas::sumArea).reversed()).forEach(pw::println);
        pw.flush();
    }
}




public class Shapes2Test {

    public static void main(String[] args) {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);


    }
}
```