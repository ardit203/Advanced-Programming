You need to implement a class `FileSystem` for a simple file system.
For your file system you must implement your own class for a file `File` that stores:

* name (`String`)
* size (`Integer`)
* creation time (`LocalDateTime`)

The class must provide the following functionalities:

* `public void addFile(char folder, String name, int size, LocalDateTime createdAt)` – method for adding a new `File` into a folder with the given name (the folder name is a single character, it can be `.` or an uppercase letter).
* `public List<File> findAllHiddenFilesWithSizeLessThen(int size)` – returns a list of all hidden files (files whose name starts with the dot character `.`) with size smaller than `size`.
* `public int totalSizeOfFilesFromFolders(List<Character> folders)` – returns the total size of all files that are located in the folders given in the list `folders`.
* `public Map<Integer, Set<File>> byYear()` – returns a `Map` in which the files are grouped by the year of creation.
* `public Map<String, Long> sizeByMonthAndDay()` – returns a `Map` in which for each month and day (regardless of the year) the total size of all files created on that month and day is calculated. The month is obtained with `getMonth()`, and the day with `getDayOfMonth()`.

The files inside each folder are ordered by creation date in ascending order, then by name lexicographically, and finally by size.
Such a comparator must be implemented in the `File` class itself.

You should also implement a `toString` representation in the following format:

```
%-10s %5dB %s` 
```