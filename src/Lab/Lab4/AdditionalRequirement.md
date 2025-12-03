### Req 1

**Additional requirement for the task `Faculty`**

* Extend the `toString` method so it also prints the student's highest grade.
  Example: instead of
  `Student{id='S001', grades=[6, 7, 8, 9, 10]}`
  it should print
  `Student{id='S001', grades=[6, 7, 8, 9, 10], maxGrade=10}`

* Implement the method `getStudentsSortedByMaxGrade()` which returns a `Set<Student>` sorted in descending order by the highest grade of the student, and then by index.

### Req 2
For the 3rd task – **TermFrequency**

Implement the methods:

- **`public Map<Integer, List<String>> byFrequency()`** - 
which returns a map where the key is the number of occurrences of the words in the text, and the value is a list of words that appear exactly that many times.
The lists of words should be sorted alphabetically, and the frequencies should be sorted in descending order.

- **`public Set<String> stopWordsUsed()`** -
which returns a set of all stop-words that actually appear in the text (even though they are not counted in the statistics).

- **`public String longestWord()`** - 
which returns the longest word that appears in the text.
In case multiple words have the same length, return the one that is lexicographically smallest.

### Req 3


Additions to task 2 from the quiz (Faculty, Students):

* Implement the method
  **`getTopNStudents(int n): Set<Student>`**, which places the students in a sorted set and returns the top **n** students (highest average).

* Implement the method
  **`getGradeDistribution(): Map<Integer, Integer>`**, which returns the grade distribution sorted by grade in ascending order.

    * If a certain grade does not appear, it should be inserted into the map with value **0**.

* Implement a method that will print the distribution of grades in the following format:

    ```
    5:  -----
    6:  ------
    7:  -------
    8:  ----------
    9:  ---------------
    10:  -------------------
    ```
  
### Req 4

### For Task 3 – `TermFrequency`

Implement the method:

```java
public Map<Character, List<String>> groupByFirstLetter()
```

which returns a map where the key is the first letter of the words, and the value is a list of the words that start with that letter.
The lists must be sorted alphabetically.



Implement the method:

```java
public Map<String, Integer> countPrefixes(int prefixLength)
```

which returns a map where the key is a prefix of a given length, and the value is the number of words in the text that start with that prefix.
Words shorter than the prefix length should be ignored.



Implement the method:

```java
public Map<Integer, Set<String>> invertIndex()
```

which inverts the frequency map, so that the frequency becomes the key, and a set of the words with that frequency becomes the value.
The sets must be sorted alphabetically.
