Implement a class `FootballTable` for processing data about multiple football matches from one league and displaying the points table according to the match results. In the class, implement:

* `public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals)` - method for adding data about a played match between the team named `homeTeam` (home team) and the team named `awayTeam` (away team), where `homeGoals` represents the number of goals scored by the home team, and `awayGoals` the number of goals scored by the away team.
* `public void printTable()` - method for printing the table based on the played (entered) matches. The table displays the rank number of the team in the table, the name (left-aligned in a field of 15 characters), the number of matches played, the number of wins, the number of draws, and the number of points earned (all numbers are printed right-aligned in a field of 5 characters). The number of points is calculated as `number_of_wins x 3 + number_of_draws x 1`. The teams are sorted by the number of points in descending order; if they have the same number of points, they are sorted by goal difference (difference between goals scored and goals conceded) in descending order, and if they have the same goal difference, they are sorted by name.


### Starter code
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Partial exam II 2016/2017
 */
public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}
```

### Solution
```java

```