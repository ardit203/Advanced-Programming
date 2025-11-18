## 1. Continuation of the task with `ResizableArray`.

You need to create a new class called `ArrayTransformer`, where you will implement the following methods:

- `map(ResizableArray source, Function mapper): ResizableArray`

- `filter(ResizableArray source, Predicate predicate): ResizableArray`

**Note:** You need to define the generics yourself.


## 2. Continuation of the task with `ResizableArray`:

You need to implement the following methods:

- `reduce(ResizableArray source, T identity, BinaryOperator accumulator): T` – reduces all elements into a single value using the provided accumulator.

- `copyIf(ResizableArray source, Predicate predicate): ResizableArray`

- `map(ResizableArray source, Function mapper): ResizableArray`

**Note:** You need to define the generics yourself.

## 3. Task extension with `Scheduler`:

You need to implement the following methods in `Scheduler`:

* `map(Scheduler source, Function mapper, Predicate filter): Scheduler` – transforms the elements with `mapper`, but only those that satisfy the `filter` condition.

* `countIf(Scheduler source, Predicate predicate): long` – returns the number of elements that satisfy the `predicate` condition.

* `merge(Scheduler first, Scheduler second): Scheduler` – a static method that merges the two schedulers into one and supports merging schedulers with different subtypes.

**Note:** You need to define the generics yourself.
