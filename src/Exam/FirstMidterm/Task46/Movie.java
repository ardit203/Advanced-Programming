package Exam.FirstMidterm.Task46;

import java.util.List;

public class Movie {
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
