# Lab Exercise 2 - Advanced Programming

## Task 1
**Implement a cinema system — `MovieTheater`.**
The cinema will maintain a list of movies that can be sorted by title, year, and ratings.

### Class `Movie`

* `title: String` — title
* `genre: String` — genre
* `year: int` — year of release
* `avgRating: double` — average rating

Implement `toString()` to print a movie in the following format:
`Title, Genre, Year, Rating`

### Class `MovieTheater`

* `movies: ArrayList<Movie>` — the list of movies in the cinema

**Methods:**

* `readMovies(InputStream is)` — reads from an `InputStream` and adds movies directly to the list using a `BufferedReader`
* `printByGenreAndTitle()` — displays the movies sorted by genre, then by title
* `printByYearAndTitle()` — displays the movies sorted by year, then by title
* `printByRatingAndTitle()` — displays the movies sorted by rating, then by title

Starter code:
```java
import java.io.*;
import java.util.*;


public class MovieTheaterTester {
    public static void main(String[] args) {
        MovieTheater mt = new MovieTheater();
        try {
            mt.readMovies(System.in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("SORTING BY RATING");
        mt.printByRatingAndTitle();
        System.out.println("\nSORTING BY GENRE");
        mt.printByGenreAndTitle();
        System.out.println("\nSORTING BY YEAR");
        mt.printByYearAndTitle();
    }
}
```

Solution:
```java
import java.io.*;
import java.util.*;

class Movie {
    private String title;
    private String genre;
    private int year;
    private double avgRating;

    public Movie(String title, String genre, int year, double avgRating) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.avgRating = avgRating;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getAvgRating() {
        return avgRating;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f", title, genre, year, avgRating);
    }
}


class MovieTheater {
    private List<Movie> movies;

    public void readMovies(InputStream in) throws IOException {
        this.movies = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String title = br.readLine();
            String genre = br.readLine();
            int year = Integer.parseInt(br.readLine());
            String[] parts = br.readLine().split(" ");
            int total = Arrays.stream(parts).mapToInt(Integer::parseInt).sum();
            double avgRating = 1.0 * total / parts.length;
            movies.add(new Movie(title, genre, year, avgRating));
        }
    }

    public void printByRatingAndTitle() {
        movies.stream()
                .sorted(Comparator.comparing(Movie::getAvgRating).reversed().thenComparing(Movie::getTitle))
                .forEach(System.out::println);
    }

    public void printByGenreAndTitle() {
        movies.stream()
                .sorted(Comparator.comparing(Movie::getGenre).thenComparing(Movie::getTitle))
                .forEach(System.out::println);
    }

    public void printByYearAndTitle() {
        movies.stream()
                .sorted(Comparator.comparing(Movie::getYear).thenComparing(Movie::getTitle))
                .forEach(System.out::println);
    }
}


public class MovieTheaterTester {
    public static void main(String[] args) {
        MovieTheater mt = new MovieTheater();
        try {
            mt.readMovies(System.in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("SORTING BY RATING");
        mt.printByRatingAndTitle();
        System.out.println("\nSORTING BY GENRE");
        mt.printByGenreAndTitle();
        System.out.println("\nSORTING BY YEAR");
        mt.printByYearAndTitle();
    }
}
```