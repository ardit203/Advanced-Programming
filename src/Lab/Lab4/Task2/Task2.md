# Lab Exercise 4 - Advanced Programming

## Task 2

**Your task is to implement the classes `Student` and `Faculty` according to the following specifications:**


* **Class `Student`:**

    * The constructor `Student(String id, List<Integer> grades)` initializes a student with the given ID and list of grades.
    * The `toString()` method returns the textual representation of the student in the format
      `Student{id='ID', grades=[grades]}`.


* **Class `Faculty`:**

    * The method `addStudent(String id, List<Integer> grades)` adds a new student to the collection with the given ID and list of grades.
      If a student with the same ID already exists, throw an exception with the message:
      `"Student with ID <id> already exists"`.
      **This method must have complexity `O(1)`.**

    * The method `addGrade(String id, int grade)` adds a new grade to an already existing student with the given ID.
      **This method must have complexity `O(1)`.**

    * The method `getStudentsSortedByAverageGrade()` returns a `Set<Student>` of students sorted in descending order based on average grade.
      If two students have the same average grade, they are sorted by number of passed courses, and if still tied, by ID in descending order.

    * The method `getStudentsSortedByCoursesPassed()` returns a `Set<Student>` of students sorted in descending order based on number of passed courses.
      If two students have the same number of passed courses, they are sorted by average grade, and then by ID in descending order.


 **Additional requirement for Friday, 13th:**

* Extend the `toString` method so it also prints the student's highest grade.
  Example: instead of
  `Student{id='S001', grades=[6, 7, 8, 9, 10]}`
  it should print
  `Student{id='S001', grades=[6, 7, 8, 9, 10], maxGrade=10}`

* Implement the method `getStudentsSortedByMaxGrade()` which returns a `Set<Student>` sorted in descending order by the highest grade of the student, and then by index.



