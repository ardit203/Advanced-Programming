# Lab Exercise 2 - Advanced Programming

## Task 5

A `Timestamp` class should represent a pair of objects — one always of type `LocalDateTime`, and the other of a generic type `T`.
The class should provide the following functionalities:
- `Timestamp(LocalDateTime time, T element) – constructor`.

- `getTime(): LocalDateTime` – returns the stored time.

- `getElement(): T` – returns the stored element.

- `compareTo(Timestamp<?> t): int` – compares two timestamps based only on their time values.

- `equals(Object o): boolean` – returns true if the time values are the same.

- `toString(): String` – returns a string representation of the timestamp, including both the time (as toString()) and the element, in the format: `time element`

**Note:** both variables `time` and `element` must be declared as `final`.

The `Scheduler` class uses the `Timestamp` class and stores multiple `Timestamp` objects.
It also has one generic parameter `T`, which represents the type of object contained in each `Timestamp`.

The `Scheduler` class should implement the following methods:
- `Scheduler()` – creates a new, empty scheduler.

- `add(Timestamp<T> t)` – adds a new object to the scheduler.

- `remove(Timestamp<T> t): boolean` – removes the corresponding element from the scheduler if it exists and returns true; otherwise returns false.

- `next(): Timestamp<T>` – returns the next Timestamp object — the one whose time is closest to the current time (now) and has not yet passed.

- `last(): Timestamp<T>` – returns the Timestamp object whose time is closest to the current time (now) and has already passed.

- `getAll(LocalDateTime begin, LocalDateTime end): List<Timestamp<T>>` – returns a list of all events whose times are between begin and end, excluding the begin and end times.