## **Qualification task for the first midterm**

Define a class **`ShapesApplication`** in which data is stored for multiple windows, where geometric images in the form of squares are drawn.

For this class, define:

-  **`ShapesApplication()` -  constructor**

- **`int readCanvases(InputStream inputStream)`** - A method that reads information from an input data stream about multiple windows in which squares are drawn.
Each line of the stream contains information about **one window**, in the format:

    ```
    canvas_id size_1 size_2 size_3 … size_n
    ```

    where:

    * `canvas_id` is the ID of the window
    * after it follow the side lengths of all squares drawn in that window.

    The method should return an **integer** that represents the **total number of squares** successfully read across all windows.

- **`void printLargestCanvasTo(OutputStream outputStream)`** - A method that prints to an output stream the **window whose squares have the largest total perimeter**.
The printing should be done in the format:

    ```
    canvas_id squares_count total_squares_perimeter
    ```

### Starter code

```java
public class Shapes1Test {

    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}
```

### Solution

```java
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import java.io.IOException;

public class Shapes1Test {

    public static void main(String[] args) throws IOException {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}


class ShapesApplication {
    TreeSet<Canvas> canvases;

    public ShapesApplication() {
        this.canvases = new TreeSet<>();
    }

    public int readCanvases(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        int count = 0;
        String line;
        while ((line = br.readLine()) != null){
            String [] tokens = line.split("\\s++");
            String id = tokens[0];
            List<Integer> squares = new ArrayList<>();
            for (int i = 1; i < tokens.length; i++) {
                squares.add(Integer.parseInt(tokens[i]));
                count++;
            }
            canvases.add(new Canvas(id, squares));
        }
        return count;
    }



    public void printLargestCanvasTo(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        pw.println(canvases.first());
        pw.flush();
    }



//    Solution using streams, but one thing also the canvases set should be a list for this to work
//    public int readCanvases(InputStream in) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
//
//        canvases = br.lines().filter(Objects::nonNull)
//                .map(CanvasFactory::createCanvas)
//                .collect(Collectors.toList());
//
//        return canvases.stream()
//                .mapToInt(Canvas::squaresCount)
//                .sum();
//    }
//
//    public void printLargestCanvasTo(PrintStream out) {
//        PrintWriter pw = new PrintWriter(out);
//        Canvas maxCanvas = canvases.stream()
//                .max(Comparator.comparingInt(Canvas::sumOfPerimeters))
//                .orElse(canvases.getLast());
//
//        pw.println(maxCanvas);
//        pw.flush();
//    }
}


class Canvas implements Comparable<Canvas> {
    private String canvasId;
    private List<Integer> squares;

    public Canvas(String canvasId) {
        this.canvasId = canvasId;
        this.squares = new ArrayList<>();
    }

    public Canvas(String canvasId, List<Integer> squares) {
        this.canvasId = canvasId;
        this.squares = squares;
    }

    public String getCanvasId() {
        return canvasId;
    }

    public List<Integer> getSquares() {
        return squares;
    }



    public int sumOfPerimeters() {
        int sum = 0;
        for (int square : squares) {
            sum += square;
        }
        return 4 * sum;
    }

//    Solution with streams
//    public int sumOfPerimeters() {
//        return squares.stream()
//                .mapToInt(s -> 4 * s)
//                .sum();
//    }

    public int squaresCount() {
        return squares.size();
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", canvasId, squaresCount(), sumOfPerimeters());
    }

    @Override
    public int compareTo(Canvas other) {
        return Integer.compare(other.sumOfPerimeters(), this.sumOfPerimeters());
    }
}
```