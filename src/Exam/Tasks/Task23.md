You need to implement a class for auditions `Audition` with the following methods:

* `void addParticipant(String city, String code, String name, int age)`
  adds a new participant with code `code`, name, and age for an audition in the given city `city`.
  In the same city, it is not allowed to add a participant with the same code as a previously added participant (the addition is ignored, and the complexity of this method must be `O(1)`).

* `void listByCity(String city)`
  prints all participants from the given city sorted by name, and if the names are the same, sorted by age
  (the complexity of this method must not exceed `O(n*log₂(n))`, where `n` is the number of participants in the given city).