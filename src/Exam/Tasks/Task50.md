Implement an application for recording student grades at a faculty. The students at the faculty can be enrolled in **three-year** or **four-year** studies. During their studies, students have **two** semesters in each year and in each semester they can have at most **3 courses**. For this purpose, define a class `Faculty` in which you will store information about the students and their grades in all semesters. For the class, implement:

* Default constructor `Faculty()`

* Method `void addStudent(String id, int yearsOfStudies)` – for adding a student to the faculty with index `id` and years of studies `yearsOfStudies`.

* Method `void addGradeToStudent(String studentId, int term, String courseName, int grade)` – for adding a grade `grade` in the course `courseName` for the student with index `studentId` in semester `term`.

    * Using an exception of type `OperationNotAllowedException`, prevent adding more than 3 grades per semester. In such a case, print a message in the format `Student [studentID] already has 3 grades in term [term]`. Using the same type of exception, prevent adding a grade in a semester greater than 6 for three-year studies, or in a semester greater than 8 for four-year studies. In this case, print the message `Term [term] is not possible for student with ID [studentId]`.
    * Graduation of the student should be detected. The student graduates when they pass 18 or 24 courses depending on how many years they study. At the moment of the student’s graduation, they should be removed from the records and a log for them should be saved in the format `Student with ID [studentID] graduated with average grade [averageGrade] in [yearsOfStudies] years`.

* Method `String getFacultyLogs()` – which returns the logs for the graduated students.

* Method `String getDetailedReportForStudent(String id)` – method that returns a detailed report for the student with index `id`. Access to the student with index `id` must have complexity `O(1)`! The detailed report is in the format:

  > Student: [id]
  > Term 1:
  > Courses for term: [count]
  > Average grade for term: [average]
  > …
  > …..
  > Term n:
  > Courses: [count]
  > Average grade for term: [average]
  > Average grade: [average grade for student]
  > Courses attended: [all_attended_courses, comma-separated, sorted lexicographically]

* Method `void printFirstNStudents(int n)` – method that prints a short report for the best `n` students (according to the number of passed courses, and if the number of passed courses is the same, according to the average grade), sorted in descending order. The short report is in the format `Student: [id] Courses passed: [coursesPassed] Average grade: [averageGrade]`.

* Method `void printCourses()` – method that prints all courses in the format `[course_name] [count_of_students] [average_grade]` for the faculty, sorted by the number of students attending the course, and if that is the same, by the average grade.

It is forbidden to use sorting functions; the sorting must be implemented using appropriate collections!
