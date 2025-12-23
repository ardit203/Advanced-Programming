Implement a generic class `Cluster` in which elements are stored that must provide their own identifier `long` and must be able to compute the distance (`double`) between two such elements. In the class, implement the following two methods:

* `void addItem(T element)` – for adding a new element to the cluster
* `void near(long id, int top)` – which prints the `top` closest elements to the element with the given identifier `id`, sorted by distance in descending order.

Then implement a class `Point2D` which represents a concrete implementation of an element in the cluster for a point in 2D space with given:

* id – `long`
* x – `float`
* y – `float`

The distance between two `Point2D` points is calculated with the formula for Euclidean distance:
$\sqrt{(x_1 - x_2)^2 + (y_1 - y_2)^2}$.
