### **Req 1**

Additional requirement for the task `Ad Network`:

* Add the ability to store the number of times an advertisement (`Ad`) has been used (displayed).
* Modify the function `placeAds` so that it reads multiple `AdRequest` objects from standard input. Before finding the appropriate ads for each AdRequest, we first want to sort the `AdRequests` **by the number of keywords**, and if they have the same number of keywords, then **by how close the floorBid of the AdRequest is to 1.00**, and if they are still equal, then **by id in ascending order**.
* After sorting them, for each AdRequest we search for the **top `k` ads (`Ad`)** as we did before for a single request, but now we first sort the ads by **how many times they have been previously displayed**, in ascending order.

---

### **Req 2**

Extension to the task `MovieTheatre`

You need to implement two methods:

* `groupByGenre(): Map<String, List<Movie>>` – returns a map of all movies where the key is the genre they belong to
* `ratingByGenre(): Map<String, Double>` – returns a map of all genres and the *sum of the ratings* of the movies that belong to them

---

### **Req 3**

Add a map **Map<String, Movie> bestMovieByGenre** inside the class **MovieTheater**, which will store the best movie (the one with the highest average rating) for each genre.

Then implement the function **printBestMovieByGenre()**, which will print the best movies by genre, sorted by the name of the genre.

Add a map **Map<String, Set<String>> actorsByMovie**, which will store the actors for a given movie.
Then implement the method:

* **void addActors(String movieTitle, List<String> actors)**
  — which will add the actors for the corresponding movie.

