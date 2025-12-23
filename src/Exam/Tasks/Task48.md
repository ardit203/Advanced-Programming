Implement a class `StudentRecords` which will read from an input stream (standard input, file, ...) data for student records.
The data contains: code (unique string), program/major (string of 3 letters), and an array of grades (integers from 6–10).
All data is separated by a single space. Example of the data format:

```text
ioqmx7 MT 10 8 10 8 10 7 6 9 9 9 6 8 6 6 9 9 8
```

Your task is to implement the following methods:

* `StudentRecords()` – default constructor
* `int readRecords(InputStream inputStream)` – method for reading the data which returns the total number of records read
* `void writeTable(OutputStream outputStream)` – method that prints all records for all students grouped by program (first the name of the program is printed), and then all records for the students from that program are printed, sorted by average grade in descending order (if they have the same average, they are sorted lexicographically by code) in the format `kod prosek`, where the average is a decimal number rounded to two decimals. Example: `jeovz8 8.47`. The programs are sorted lexicographically. The complexity of the method must not exceed $O(N)$ with respect to the number of records.
* `void writeDistribution(OutputStream outputStream)` – method for printing the distribution of the number of grades per program, where the programs are sorted by the number of tens (grade 10) in ascending order (first is the program with the highest number of grade ten). The distribution of grades is printed in the following format:

    ```text
    PROGRAM
    [grade right-aligned in a field of width 2] | [one * character for every 10 grades] ([total grades])
    ```

    Example:

    ```text
    KNI
     6 | ***********(103)
     7 | ******************(173)
     8 | *******************(184)
     9 | *****************(161)
    10 | **************(138)
    ```

The complexity of this method must not exceed $O(N * M * log_2(M))$ for N records and M programs.
