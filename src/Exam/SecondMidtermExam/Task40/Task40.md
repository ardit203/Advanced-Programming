Implement a generic class `Cluster` in which elements are stored that must provide their own identifier `long` and must be able to compute the distance (`double`) between two such elements. In the class, implement the following two methods:

* `void addItem(T element)` – for adding a new element to the cluster
* `void near(long id, int top)` – which prints the `top` closest elements to the element with the given identifier `id`, sorted by distance in ascending order.

Then implement a class `Point2D` which represents a concrete implementation of an element in the cluster for a point in 2D space with given:

* id – `long`
* x – `float`
* y – `float`

The distance between two `Point2D` points is calculated with the formula for Euclidean distance:
$\sqrt{(x_1 - x_2)^2 + (y_1 - y_2)^2}$.

### Starter code
```java
import java.util.*;

/**
 * January 2016 Exam problem 2
 */
public class ClusterTest {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Cluster<Point2D> cluster = new Cluster<>();
    int n = scanner.nextInt();
    scanner.nextLine();
    for (int i = 0; i < n; ++i) {
      String line = scanner.nextLine();
      String[] parts = line.split(" ");
      long id = Long.parseLong(parts[0]);
      float x = Float.parseFloat(parts[1]);
      float y = Float.parseFloat(parts[2]);
      cluster.addItem(new Point2D(id, x, y));
    }
    int id = scanner.nextInt();
    int top = scanner.nextInt();
    cluster.near(id, top);
    scanner.close();
  }
}
```

### Solution

```java
package Exam.SecondMidtermExam.Task40;

import java.util.*;
import java.util.stream.Collectors;

interface ICluster<T> {
    long getId();

    double getDistance(T other);
}


class Point2D implements ICluster<Point2D> {
    long id;
    float x;
    float y;

    public Point2D(long id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }


    @Override
    public long getId() {
        return id;
    }

    @Override
    public double getDistance(Point2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
//        double dist = Math.hypot(dx, dy);

        return Math.sqrt(dx * dx + dy * dy);
    }
}

class Cluster<T extends ICluster<T>> {
    private Map<Long, T> elements;

    public Cluster() {
        this.elements = new HashMap<>();
    }


    public void addItem(T element) {
        elements.putIfAbsent(element.getId(), element);
    }

    public void near(long id, int top) {
        T element = elements.get(id);

        List<T> result = elements.values()
                .stream()
                .filter(e -> e.getId() != id)
                .sorted(Comparator.comparingDouble(left -> left.getDistance(element)))
                .limit(top)
                .collect(Collectors.toList());

        for (int i = 0; i < result.size(); i++) {
            T res = result.get(i);
            System.out.printf("%d. %d -> %.3f\n", i + 1, res.getId(), res.getDistance(element));
        }
    }
}


public class ClusterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cluster<Point2D> cluster = new Cluster<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            long id = Long.parseLong(parts[0]);
            float x = Float.parseFloat(parts[1]);
            float y = Float.parseFloat(parts[2]);
            cluster.addItem(new Point2D(id, x, y));
        }
        int id = scanner.nextInt();
        int top = scanner.nextInt();
        cluster.near(id, top);
        scanner.close();
    }
}
```