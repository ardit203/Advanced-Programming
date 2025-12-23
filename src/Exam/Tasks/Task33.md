Write a class `TaskManager` that will be used for managing tasks of a given user. For the class, implement the following methods:

* `readTasks(InputStream inputStream)` – method for reading the user’s tasks, where each task is in the following format: `[category][task_name],[description],[deadline],[priority]`. The deadline and the priority are optional fields.

    * A given task must not be allowed to have a deadline that has already passed. In such a case, an exception of type `DeadlineNotValidException` should be thrown. The exception should be caught in an appropriate place so that the reading of the remaining tasks is not interrupted!!!

* `void printTasks(OutputStream os, boolean includePriority, boolean includeCategory)` – method for printing the tasks.

    * If `includeCategory` is `true`, the tasks should be printed grouped by categories; otherwise, all entered tasks are printed.
    * If `includePriority` is `true`, the tasks should be printed sorted by priority (where 1 is the highest priority), and the tasks that have no priority or have the same priority are sorted in ascending order according to the time distance between the deadline and the current date, i.e. the tasks with a deadline closest to today’s date are printed first.
    * If `includePriority` is `false`, they are printed in ascending order according to the time distance between the deadline and the current date.
    * When printing the tasks, the default option for `toString` is used (if you are working in IntelliJ), with the note that you must pay attention to the names of the variables.

**Bonus:** Using software design patterns for the representation of the tasks and for their creation.
