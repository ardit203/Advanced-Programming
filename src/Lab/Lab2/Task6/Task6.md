# Lab Exercise 2 - Advanced Programming

## Task 6


You need to develop a `Queue` class that represents a queue data structure, implemented internally as a linked list.
First, you should write a class for a single element (node) in the list called `Node`.

The `Node` class should have one generic parameter `T`, which refers to the element stored in the node, and one reference to the next node in the list.

Formally, the `Node` class should provide the following methods:

- `Node(T element, Node<T> next)` – constructor that initializes both variables

- `getElement(): T`

- `getNext(): Node<T>`

- `setNext(Node<T> next)`


Using the `Node` class, we now write the `Queue` class with the following methods:

- `Queue()` – creates a new empty queue.

- `isEmpty(): boolean` – returns true if the queue is empty (contains no elements).

- `enqueue(T element)` – adds an element to the end of the queue.

- `dequeue(): T` – removes and returns the element at the front of the queue;
if the queue is empty, it throws an EmptyQueueException.

- `peek(): T` – returns the element at the front of the queue (without modifying it);
if the queue is empty, it throws an EmptyQueueException.

- `inspect(): T` – returns the element at the end of the queue (without modifying it);
if the queue is empty, it throws an EmptyQueueException.

- `count(): int` – returns the number of elements currently in the queue.

**Note:**
The `Queue` class must have one generic type parameter, which represents the type of the elements stored in the queue.

**Important:**
You must not use any built-in data structures such as `ArrayList` or `LinkedList` to implement the `Queue` class.
Instead, the queue should be implemented manually using the `Node` class (linked list structure) that you defined earlier.