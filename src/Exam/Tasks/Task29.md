You need to (finish) implementing the application for tracking the work engagement of employees in an IT company.
Recommendation: To avoid problems with identical class names, use separate packages for the two tasks if you want to solve them separately.

For that purpose, implement the class `PayrollSystem` in which information about employees in the company will be stored.
As in the previous task, there are two types of employees: `HourlyEmployee` and `FreelanceEmployee`.
The salary calculation for both types of employees is the same as in the previous task.
For the class `PayrollSystem`, implement:

* `PayrollSystem(Map<String,Double> hourlyRateByLevel, Map<String,Double> ticketRateByLevel)` – same as in the previous task
* `Employee createEmployee (String line)` – a method which, based on an input string containing the information for a given employee, will create and return an object of class Employee. Additionally, the method will store the employee in the payroll system.
  The employee information is in the same format as in the first task, except that in this case there may be a bonus for the employee, which is separated by a space from the employee’s information.
  There are two types of bonuses an employee can receive:

    * Fixed monetary bonus (written as a number). Example: `H;ID;level;hours; 100` (in this case the employee receives a fixed salary bonus of 100$)
    * Percentage bonus (written as a number with a percent sign). Example: `F;ID;level;ticketPoints1;ticketPoints2;...;ticketPointsN; 10%` (in this case the employee receives a percentage bonus of 10% of their salary).
* In the previous method, with an exception of type `BonusNotAllowedException`, prevent the creation of an employee who is assigned a fixed bonus greater than 1000$ or a percentage bonus greater than 20%.
* `Map<String, Double> getOvertimeSalaryForLevels ()` – a method which returns a map where the key is the employee level, and the value is the total amount the company has paid for overtime work for the employees of that level.
* `void printStatisticsForOvertimeSalary ()` – a method which prints statistics (minimum, maximum, sum, average) of the overtime payments for all employees in the company.
* `Map<String, Integer> ticketsDoneByLevel()` – a method which returns a map where the key is the employee level, and the value is the number of ticket points completed by the employees of the corresponding level.
* `Collection<Employee> getFirstNEmployeesByBonus (int n)` – a method which returns a sorted collection of the first n employees sorted in descending order by the bonus they received on their salary.
