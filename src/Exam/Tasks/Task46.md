Implement a class `MoviesList` in which a list of movies is stored (class `Movie` – for each movie its title and a list of ratings (integers from 1 to 10) are given) and it has the following methods:

* `public void addMovie(String title, int[] ratings)` – method for adding a new movie to the list (title and an array of ratings)
* `public List<Movie> top10ByAvgRating()` – method that returns a list of the 10 movies with the best average rating, sorted in descending order by rating (if two movies have the same average rating, they are sorted lexicographically by name)
* `public List<Movie> top10ByRatingCoef()` – method that returns a list of the 10 movies with the best rating coefficient (calculated as `average rating of the movie x total number of ratings of the movie / maximum number of ratings (among all movies in the list)`)

For the class `Movie`, the `toString()` method should be overridden to return an appropriate representation (see the sample output).
