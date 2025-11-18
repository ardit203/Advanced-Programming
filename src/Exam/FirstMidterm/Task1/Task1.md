## **Qualification task for the first midterm**

Define a class **`ShapesApplication`** in which data is stored for multiple windows, where geometric images in the form of squares are drawn.

For this class, define:

### **`ShapesApplication()` — constructor**

### **`int readCanvases(InputStream inputStream)`**

A method that reads information from an input data stream about multiple windows in which squares are drawn.

Each line of the stream contains information about **one window**, in the format:

```
canvas_id size_1 size_2 size_3 … size_n
```

where:

* `canvas_id` is the ID of the window
* after it follow the side lengths of all squares drawn in that window.

The method should return an **integer** that represents the **total number of squares** successfully read across all windows.

### **`void printLargestCanvasTo(OutputStream outputStream)`**

A method that prints to an output stream the **window whose squares have the largest total perimeter**.

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
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Window {
    private String windowId;
    private List<Integer> squares;

    public Window(String windowId, List<Integer> squares) {
        this.windowId = windowId;
        this.squares = squares;
    }

    public static Window create(String line) {
        String[] tokens = line.split("\\s++");
        String id = tokens[0];

        List<Integer> sq = Arrays.stream(tokens).skip(1).map(Integer::parseInt).collect(Collectors.toList());

        return new Window(id, sq);
    }

    public String getWindowId() {
        return windowId;
    }

    public List<Integer> getSquares() {
        return squares;
    }

    public int perimeter() {
        return squares.stream().mapToInt(s -> 4 * s).sum();
    }
}

class ShapesApplication {
    private List<Window> windows;


    public int readCanvases(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        windows = br.lines().map(Window::create).collect(Collectors.toList());
        return windows.stream().mapToInt(w -> w.getSquares().size()).sum();
    }

    public void printLargestCanvasTo(PrintStream out) {
        int max = windows.stream().mapToInt(Window::perimeter).max().orElse(0);
        Window window = windows.stream().filter(w -> w.perimeter() == max).findFirst().orElse(windows.get(windows.size() - 1));

        PrintWriter pw = new PrintWriter(out);
        pw.printf("%s %d %d\n", window.getWindowId(), window.getSquares().size(), max);
        pw.flush();
    }
}

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