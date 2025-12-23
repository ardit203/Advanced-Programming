You need to write the class **`Student`** in which the following information will be stored:

* index of a FINKI student (a string consisting of six digits)
* a list of points earned on laboratory exercises for some FINKI course. The course has a maximum of 10 laboratory exercises.

For the class, write the constructor
**`Student(String index, List<Integer> points)`**.

---

You need to write a class **`LabExercises`** in which a collection of students is stored. For the class, write the following methods:

* **`public void addStudent (Student student)`** – method for adding a new student to the collection
* **`public void printByAveragePoints (boolean ascending, int n)`** – method that prints the first `n` students sorted by their total points, and if the total points are equal, by index; in ascending order if `ascending` is true, otherwise in descending order.
  The total points are calculated as the sum of the points divided by 10.
* **`public List<Student> failedStudents ()`** – method that returns a list of students who did not receive a signature (they have more than 2 absences), sorted first by index and then by total points.
* **`public Map<Integer,Double> getStatisticsByYear()`** – method that returns a map of the average total points of the students grouped by year of study. Students who did not receive a signature should be ignored.
