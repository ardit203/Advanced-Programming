package Lab.Lab3.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MovieTheater {

    private List<Movie> movies;

    public void readMovies(InputStream in) throws IOException {
        this.movies = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String title = br.readLine();
            String genre = br.readLine();
            int year = Integer.parseInt(br.readLine());
            String [] parts = br.readLine().split(" ");
            int total = Arrays.stream(parts).mapToInt(Integer::parseInt).sum();
            double avgRating = 1.0 * total / parts.length;
            movies.add(new Movie(title, genre, year,avgRating));
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
