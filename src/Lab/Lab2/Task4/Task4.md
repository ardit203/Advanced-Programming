# Lab Exercise 2 - Advanced Programming

## Task 4

Write a class `ResizableArray` that represents an array with a variable length.
The class should be able to store elements of any type (it must be generic with one type parameter `T`) and should define the following methods:
- `ResizableArray()` – creates a new empty array.

- `addElement(T element)` – adds a new element to the array.
(If there’s not enough space, the capacity of the array should increase.)

- `removeElement(T element): boolean` –
if such an element exists, remove one instance of it and return `true`; otherwise return `false`.
(If there’s too much unused space, reduce the array’s capacity.)

- `contains(T element): boolean` – returns `true` if the element exists in the array.

- `toArray(): Object[]` – returns all elements as a regular array.

- `isEmpty()` – returns true if the array has no elements.

- `count(): int` – returns the number of elements currently in the array.

- `elementAt(int idx): T` – returns the element at the given position;
if such an element does not exist, throw an `ArrayIndexOutOfBoundsException`.
(The elements in the array are positioned at indices [0, count()].)

**Note:** To store the elements, you must use a regular array of type `T[]` elements.
**You are not allowed to use** `ArrayList<T>`, and the array must be declared as `private`.

Additionally, the class `ResizableArray` should include one static method:
- `static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src)`

This method must copy all elements from `src` (the source array) into `dest` (the destination array).
The source (`src`) remains unchanged, while the destination (`dest`) keeps its existing elements and adds all elements from the source.

Next, you need to write a class `IntegerArray` that inherits from the class `ResizableArray` `IntegerArray extends ResizableArray<Integer>`. This class is used for storing integer values and should provide the following methods:

- `sum(): double` – returns the sum of all elements in the array.
- `mean(): double` – returns the average of all elements in the array.
- `countNonZero(): int` – returns the number of elements in the array that have a value different from zero.
- `distinct(): IntegerArray` – returns a new object that contains the same elements as this, but without duplicate elements.
- `increment(int offset): IntegerArray` – returns a new object that contains all the elements of this, but with each element increased by the given offset.