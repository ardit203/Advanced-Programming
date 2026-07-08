Implement a class `Names` with the following methods:

* `public void addName(String name)` – adds a name
* `public void printN(int n)` – prints all names that appear `n` or more times, sorted lexicographically by the name. At the end of the word, in parentheses, the number of occurrences is printed, and after that the number of unique letters in the word (case-insensitive).
* `public String findName(int len, int x)` – returns the name that is at position `x` (starting from 0) in the list of unique names sorted lexicographically, after deleting all names with length greater than or equal to `len`.
  The position `x` may be greater than the number of remaining names; in that case, counting continues from the start of the list.
  Example: for a list with 3 names `A, B, C`, if `x = 7`, the result is `B`.
  Sequence: `A0, B1, C2, A3, B4, C5, A6, B7`.

### Starter code
```java
import java.util.*;

public class NamesTest {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    scanner.nextLine();
    Names names = new Names();
    for (int i = 0; i < n; ++i) {
      String name = scanner.nextLine();
      names.addName(name);
    }
    n = scanner.nextInt();
    System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
    names.printN(n);
    System.out.println("===== FIND NAME =====");
    int len = scanner.nextInt();
    int index = scanner.nextInt();
    System.out.println(names.findName(len, index));
    scanner.close();

  }
}
```

### Solution
```java
import java.util.*;
import java.util.stream.Collectors;

class NameInfo {
    private int occurrences;
    private Set<Character> uniqueLetters;

    public NameInfo(String name) {
        occurrences = 0;
        uniqueLetters = new HashSet<>();
        for (char c : name.toCharArray()) {
            uniqueLetters.add(Character.toLowerCase(c));
        }
    }

    public int getOccurrences() {
        return occurrences;
    }

    public Set<Character> getUniqueLetters() {
        return uniqueLetters;
    }

    public int numUniqueLetters() {
        return uniqueLetters.size();
    }

    public void incrementOccurrences() {
        occurrences++;
    }

    @Override
    public String toString() {
        return String.format("(%d) %d", occurrences, numUniqueLetters());
    }
}

class Names {
    private Map<String, NameInfo> names;

    public Names() {
        this.names = new TreeMap<>();
    }


    public void addName(String name) {
        names.computeIfAbsent(name, k -> new NameInfo(name)).incrementOccurrences();
    }


    public void printN(int n) {
        names.entrySet()
                .stream()
                .filter(e -> e.getValue().getOccurrences() >= n)
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

    public String findName(int len, int x) {
        List<String> res = names.keySet()
                .stream()
                .filter(name -> name.length() < len)
                .collect(Collectors.toCollection(ArrayList::new));

        int index = 0;
        int count = 0;
        while (true) {
            if (index == res.size()) {
                index = 0;
            }
            if (count == x) {
                return res.get(index);
            }
            index++;
            count++;
        }
    }
}

public class NamesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Names names = new Names();
        for (int i = 0; i < n; ++i) {
            String name = scanner.nextLine();
            names.addName(name);
        }
        n = scanner.nextInt();
        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
        names.printN(n);
        System.out.println("===== FIND NAME =====");
        int len = scanner.nextInt();
        int index = scanner.nextInt();
        System.out.println(names.findName(len, index));
        scanner.close();

    }
}
```