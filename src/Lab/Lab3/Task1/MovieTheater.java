package Lab.Lab3.Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class MovieTheater {
    //additional Requirement
    private Map<String, Movie> bestMovieByGenre;
    private Map<String, Set<String>> actorsByMovie;

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

    //Additional Requirement
    public Map<String, List<Movie>> groupByGenre() {

        return movies.stream().collect(Collectors.groupingBy(
                Movie::getGenre,
                Collectors.toCollection(ArrayList::new)
        ));
    }

    Map<String, Double> ratingByGenre() {
        return movies.stream().collect(Collectors.groupingBy(
                Movie::getGenre,
                TreeMap::new,
                Collectors.summingDouble(Movie::getAvgRating)
        ));
    }

    public void printBestMovieByGenre() {
        bestMovieByGenre = movies.stream().collect(Collectors.groupingBy(
                Movie::getGenre,
                TreeMap::new,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingDouble(Movie::getAvgRating)),
                        Optional::get
                )
        ));

        bestMovieByGenre.entrySet().stream()
                .forEach(e -> System.out.printf("%s - %s", e.getKey(), e.getValue()));
    }

    public void addActors(String movieTitle, List<String> actors){
        actorsByMovie.computeIfAbsent(movieTitle, k -> new HashSet<>()).addAll(actors);
    }
}
