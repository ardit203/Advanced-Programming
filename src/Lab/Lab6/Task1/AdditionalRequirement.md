### Req 1
Implement the following methods:

* `getTotalCredits(): int` – returns the total number of credits from all courses.
* `getDepartmentsWithAtLeastOneHardCourse(int difficultyThreshold): List<Department>` – returns a list of departments that have at least one hard course (a course whose difficulty exceeds `difficultyThreshold`).

The methods must be implemented using the **Streams API**.

### Req 2

Implement the following methods:

- `getCoursesWithNameContaining(String substring): List<Course>` which returns all courses whose names contain the given substring.
- `getTopDepartmentByAverageDifficulty(): Optional<Department>` which returns the department with the highest average course difficulty.
Use methods from the Streams API!