Define the class `GenericCollection` in which elements will be stored that are comparable and that have a creation timestamp.
The class should provide the following methods:

* `void addGenericItem(String category, T element)` – method for adding a new element in a given category.
* `Collection<T> findAllBetween(LocalDateTime from, LocalDateTime to)` – method that returns a collection of all elements that are in the interval of dates given as arguments to the function.
* `Collection<T> itemsFromCategories(List<String> categories)` – method that returns the elements that are in the categories given as an argument to the function.
* `public Map<String, Set<T>> byMonthAndDay()` – returns a map in which the elements are grouped according to their timestamp (specifically, the month and day concatenated with `-` between them, e.g. `12-30`, regardless of the year). The month is obtained by calling `getMonth()`, and the day by `getDayOfMonth()`.
* `public Map<Integer, Long> countByYear()` – returns a map where the keys are all years in which some element was created, and the corresponding value is the number of elements created in that year.

Everywhere there is a collection of elements, the elements must be sorted in **descending** order!
