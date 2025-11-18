Implement a class `Canvas` that stores different shapes.
For each shape, store:
- `id: String`
- `color: Color` (an `enum` is provided)

All shapes must implement two interfaces:
 - `Scalable` — defined with a single method v`oid scale(float scaleFactor)` for appropriately increasing/decreasing the size of the shape by the given factor.
 - `Stackable` — defined with a single method `float weight()` which returns the weight of the shape (computed as the area of the corresponding shape).

 In the Canvas class implement the following methods:
 - `void add(String id, Color color, float radius)` — to add a circle.
 - `void add(String id, Color color, float width, float height)` — to add a rectangle.
       - When adding a new shape, it must be placed in the list of shapes at the appropriate position according to its weight. The elements must always be ordered by weight in descending order.
 - `void scale(String id, float scaleFactor)` — scales the shape with the given id by the given `scaleFactor`. If necessary, the shapes must be repositioned to preserve the required ordering of elements.
     You must not use sorting of the list.
 - `toString()` — returns a string composed of all figures in order. For each figure, append:

   - `C: [id:%5s] [color:%10s] [weight:%10.2f]` if it is a circle
   - `R: [id:%5s] [color:%10s] [weight:%10.2f]` if it is a rectangle

**You are not allowed to use instanceof in your solution. If you do, it will be considered incorrect.**

### Starter code
```java
import java.util.Scanner;

enum Color {
	RED, GREEN, BLUE
}
public class ShapesTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Canvas canvas = new Canvas();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			int type = Integer.parseInt(parts[0]);
			String id = parts[1];
			if (type == 1) {
                Color color = Color.valueOf(parts[2]);
				float radius = Float.parseFloat(parts[3]);
				canvas.add(id, color, radius);
			} else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
				float width = Float.parseFloat(parts[3]);
				float height = Float.parseFloat(parts[4]);
				canvas.add(id, color, width, height);
			} else if (type == 3) {
				float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
				System.out.print(canvas);
				canvas.scale(id, scaleFactor);
				System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
				System.out.print(canvas);
			}

		}
	}
}

enum Color {
	RED, GREEN, BLUE
}

class Canvas {

}
```


### Solution
```java
import java.util.Scanner;
import java.util.Arrays;

enum Color {
	RED, GREEN, BLUE
}
public class ShapesTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Canvas canvas = new Canvas();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			int type = Integer.parseInt(parts[0]);
			String id = parts[1];
			if (type == 1) {
                Color color = Color.valueOf(parts[2]);
				float radius = Float.parseFloat(parts[3]);
				canvas.add(id, color, radius);
			} else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
				float width = Float.parseFloat(parts[3]);
				float height = Float.parseFloat(parts[4]);
				canvas.add(id, color, width, height);
			} else if (type == 3) {
				float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
				System.out.print(canvas);
				canvas.scale(id, scaleFactor);
				System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
				System.out.print(canvas);
			}

		}
	}
}



interface Stackable {
    public float weight();
}

interface Scalable {
    public void scale(float scaleFactor);
}




class Canvas {
    private Shape[] shapes;
    private int size;

    public Canvas() {
        this.shapes = new Shape[10];
        this.size = 0;
    }

    private int index(float weight) {
        for (int i = 0; i < size; i++) {
            if (shapes[i].weight() < weight) {
                return i;
            }
        }
        return size;
    }

    private void shift(Shape s) {
        int j = index(s.weight());
        for (int i = size; i > j; i--) {
            shapes[i] = shapes[i - 1];
        }
        shapes[j] = s;
        size++;
    }

    private void resize() {
        if (size == shapes.length) {
            shapes = Arrays.copyOf(shapes, size * 2);
        }
    }

    void add(String id, Color color, float radius) {
        resize();
        Circle circle = new Circle(id, color, radius);
        if(size == 0){
            shapes[size++] = circle;
            return;
        }
        shift(circle);
    }

    void add(String id, Color color, float width, float height) {
        resize();
        Rectangle rectangle = new Rectangle(id, color, width, height);
        if(size == 0){
            shapes[size++] = rectangle;
            return;
        }
        shift(rectangle);
    }

    void scale(String id, float scaleFactor) {
        Shape s = null;

        int index = -1;
        for (int i = 0; i < size; i++) {
            if (shapes[i].getId().equals(id)) {
                s = shapes[i];
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        for (int i = index; i < size - 1; i++) {
            shapes[i] = shapes[i + 1];
        }

        shapes[--size] = null;
        s.scale(scaleFactor);
        shift(s);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        

        for (int i = 0; i < size; i++) {
            sb.append(shapes[i]);
        }
        return sb.toString();
    }
}


abstract class Shape implements Scalable, Stackable {
    private String id;
    private Color color;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
}

class Circle extends Shape {
    private float radius;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    @Override
    public void scale(float scaleFactor) {
        radius*=scaleFactor;
    }

    @Override
    public float weight() {
        return (float) (Math.PI * radius * radius);
    }

    @Override
    public String toString() {
        return String.format("C: %-5s%-10s%10.2f\n",getId(), getColor(), weight());
    }
}

class Rectangle extends Shape {
    private float width;
    private float height;

    public Rectangle(String id, Color color, float width, float height) {
        super(id, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void scale(float scaleFactor) {
        width *= scaleFactor;
        height *= scaleFactor;
    }

    @Override
    public float weight() {
        return width * height;
    }

    @Override
    public String toString() {
        return String.format("R: %-5s%-10s%10.2f\n",getId(), getColor(), weight());
    }
}
```