It is required to define two task schedulers that implement the interface `TaskScheduler`.
`TaskScheduler` is a generic interface for scheduling tasks (`Task`) with a single method `schedule`, which receives an array of tasks and is expected to return a list of those same tasks, scheduled according to the scheduling criteria.

A task (`Task`) is an interface that implements a method which returns the execution order number of the task.
There are two types of tasks (`TimedTask` and `PriorityTask`).
The order number of a `TimedTask` is defined by its execution time (`time`), while for a `PriorityTask` it is defined by the priority of execution (`priority`).

The first scheduler schedules the tasks in such a way that it sorts them by their order number.
Its implementation needs to be provided using an anonymous class (in the method `getOrdered` of the class Schedulers).

The second scheduler preserves the order of the received tasks, but filters (removes) all tasks with an order number greater than a given threshold (`order`).
Its implementation must be provided using a lambda expression (in the method `getFiltered` of the class Schedulers).

Scheduled tasks are executed using the generic class `TaskRunner`.
For this class, it is only necessary to define the type parameter.
