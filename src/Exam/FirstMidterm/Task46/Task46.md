Implement a class `MoviesList` in which a list of movies is stored (class `Movie` – for each movie its title and a list of ratings (integers from 1 to 10) are given) and it has the following methods:

* `public void addMovie(String title, int[] ratings)` – method for adding a new movie to the list (title and an array of ratings)
* `public List<Movie> top10ByAvgRating()` – method that returns a list of the 10 movies with the best average rating, sorted in descending order by rating (if two movies have the same average rating, they are sorted lexicographically by name)
* `public List<Movie> top10ByRatingCoef()` – method that returns a list of the 10 movies with the best rating coefficient (calculated as `average rating of the movie x total number of ratings of the movie / maximum number of ratings (among all movies in the list)`)

For the class `Movie`, the `toString()` method should be overridden to return an appropriate representation (see the sample output).

### Starter code
```java
import java.util.*;

public class MoviesTest {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    MoviesList moviesList = new MoviesList();
    int n = scanner.nextInt();
    scanner.nextLine();
    for (int i = 0; i < n; ++i) {
      String title = scanner.nextLine();
      int x = scanner.nextInt();
      int[] ratings = new int[x];
      for (int j = 0; j < x; ++j) {
        ratings[j] = scanner.nextInt();
      }
      scanner.nextLine();
      moviesList.addMovie(title, ratings);
    }
    scanner.close();
    List<Movie> movies = moviesList.top10ByAvgRating();
    System.out.println("=== TOP 10 BY AVERAGE RATING ===");
    for (Movie movie : movies) {
      System.out.println(movie);
    }
    movies = moviesList.top10ByRatingCoef();
    System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
    for (Movie movie : movies) {
      System.out.println(movie);
    }
  }
}
```

### Starter code
```java
package Exam.FirstMidterm.Task46;

import java.util.*;
import java.util.stream.Collectors;

class Movie {
    private String title;
    private List<Integer> ratings;
    private double avgRating;

    public Movie(String title, List<Integer> ratings) {
        this.title = title;
        this.ratings = ratings;
        this.avgRating = ratings.stream().mapToInt(r -> r).average().orElse(0);
    }

    public String getTitle() {
        return title;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public int getRatingsCount() {
        return ratings.size();
    }

    public double getCoeff(int max) {
        return avgRating * getRatingsCount() / max;
    }

    @Override
    public String toString() {
        //Story of Women (1989) (6.63) of 8 ratings
        return String.format("%s (%.2f) of %d ratings", title, avgRating, getRatingsCount());
    }
}


class MoviesList {
    private List<Movie> movies;

    public MoviesList() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(String title, int[] ratings) {
        movies.add(
                new Movie(title,
                        Arrays.stream(ratings)
                                .boxed()
                                .collect(Collectors.toList())
                )
        );
    }

    public List<Movie> top10ByAvgRating() {
        return movies.stream()
                .sorted(Comparator.comparing(Movie::getAvgRating).reversed().thenComparing(Movie::getTitle))
                .limit(10)
                .collect(Collectors.toList());
    }

    public int maxNumberOfRatings() {
        return movies.stream()
                .mapToInt(Movie::getRatingsCount)
                .max().orElse(0);
    }

    public List<Movie> top10ByRatingCoef() {
        int max = maxNumberOfRatings();

        return movies.stream()
                .sorted(Comparator.comparing((Movie m) -> m.getCoeff(max)).reversed().thenComparing(Movie::getTitle))
                .limit(10)
                .collect(Collectors.toList());
    }

}

public class MoviesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoviesList moviesList = new MoviesList();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String title = scanner.nextLine();
            int x = scanner.nextInt();
            int[] ratings = new int[x];
            for (int j = 0; j < x; ++j) {
                ratings[j] = scanner.nextInt();
            }
            scanner.nextLine();
            moviesList.addMovie(title, ratings);
        }
        scanner.close();
        List<Movie> movies = moviesList.top10ByAvgRating();
        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        movies = moviesList.top10ByRatingCoef();
        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
```