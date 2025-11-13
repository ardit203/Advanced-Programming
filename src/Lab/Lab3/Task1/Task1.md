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
