Implement a class `Names` with the following methods:

* `public void addName(String name)` – adds a name
* `public void printN(int n)` – prints all names that appear `n` or more times, sorted lexicographically by the name. At the end of the word, in parentheses, the number of occurrences is printed, and after that the number of unique letters in the word (case-insensitive).
* `public String findName(int len, int x)` – returns the name that is at position `x` (starting from 0) in the list of unique names sorted lexicographically, after deleting all names with length greater than or equal to `len`.
  The position `x` may be greater than the number of remaining names; in that case, counting continues from the start of the list.
  Example: for a list with 3 names `A, B, C`, if `x = 7`, the result is `B`.
  Sequence: `A0, B1, C2, A3, B4, C5, A6, B7`.
