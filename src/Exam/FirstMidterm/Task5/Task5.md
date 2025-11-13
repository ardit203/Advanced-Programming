You need to implement a generic class **MinMax** from two comparable objects (minimum/maximum).
For this class, you need to implement:

* `MinMax()` – default constructor
* `void update(T element)` – method for updating the current minimum/maximum
* `T max()` – returns the largest element
* `T min()` – returns the smallest element
* override the method `toString()`, which returns a string composed of the minimum and maximum element and the number of elements processed in the `update` method that are *different from* the current minimum/maximum, separated by a space.

In the class, you are **not allowed to store the elements** processed in the `update` method, except for the current minimum/maximum.

