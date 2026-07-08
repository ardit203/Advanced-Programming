package Exam.FirstMidterm.Task46;

import java.util.*;
import java.util.stream.Collectors;

public class MoviesList {
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